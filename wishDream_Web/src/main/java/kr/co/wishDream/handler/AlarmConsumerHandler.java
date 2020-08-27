package kr.co.wishDream.handler;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverRecord;

@Component
@RequiredArgsConstructor
public class AlarmConsumerHandler extends TextWebSocketFrame implements WebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AlarmConsumerHandler.class);
	private Flux<ReceiverRecord<Integer, String>> reactiveKafkaReceiver;
	
	private ObjectMapper objectMapper;
	
	public Mono<ServerResponse> emitMessage() {
		try {
			String message = objectMapper.writeValueAsString(
						reactiveKafkaReceiver
							.doOnNext(onNext -> LOG.info("## KAFKA-RECEIVER ## SUBSCRIBE MESSAGE ="+onNext.value()))
							.doOnError(onError -> LOG.error("## KAFKA MESSAGE ## SUBSCRIBE MESSAGE = "+onError.getStackTrace()))
							.map(record -> { return record.value(); }).collectList().block()
					);
			return ServerResponse
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(message), ArrayList.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String emitMessageSession() {
		try {
			return objectMapper.writeValueAsString(
						reactiveKafkaReceiver
							.doOnNext(onNext -> LOG.info("## KAFKA-RECEIVER ## SUBSCRIBE MESSAGE ="+onNext.value()))
							.doOnError(onError -> LOG.error("## KAFKA MESSAGE ## SUBSCRIBE MESSAGE = "+onError.getStackTrace()))
							.map(record -> { return record.value(); }).collectList().block()
					);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		Flux<String> response = Flux.just(emitMessageSession());
		return session.send(response.map(session::textMessage)).then();
	}
}
