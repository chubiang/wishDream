package kr.co.wishDream.handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
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
public class KafkaMessageHandler extends TextWebSocketFrame implements WebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageHandler.class);
	private Flux<ReceiverRecord<Integer, String>> reactiveKafkaReceiver;
	
	private ObjectMapper objectMapper;
	
	public String emitMessage() {
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
		Flux<String> response = Flux.just(emitMessage());
		return session.send(response.map(session::textMessage)).then();
	}
}
