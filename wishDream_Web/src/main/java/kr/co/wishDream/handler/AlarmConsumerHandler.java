package kr.co.wishDream.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.wishDream.config.KafkaConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

@Component
public class AlarmConsumerHandler implements WebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AlarmConsumerHandler.class);
	
	@Autowired
	AlarmProducerHandler producerhandler;
	
	@Autowired
	KafkaConfig config;
	
	@Value("${kafka.topics}") 
	String[] topics;
	
	@Autowired
	ReceiverOptions<Integer, String> receiverOptions;
	
	private ObjectMapper objectMapper;
	private SimpleDateFormat dateFormat;
	
	public Mono<ServerResponse> emitMessage() {
		try {
			producerhandler.sendMessage();
			Flux<ReceiverRecord<Integer, String>> reactiveKafkaReceiver = 
					KafkaReceiver.create(receiverOptions).receive();
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
		int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
		dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
		try {

			Flux<ReceiverRecord<Integer, String>> reactiveKafkaReceiver = 
					KafkaReceiver.create(receiverOptions).receive();
			
			reactiveKafkaReceiver.subscribe(record -> {
	             ReceiverOffset offset = record.receiverOffset();
	             System.out.printf("Received message: topic-partition=%s offset=%d timestamp=%s key=%d value=%s\n",
	                     offset.topicPartition(),
	                     offset.offset(),
	                     dateFormat.format(new Date(record.timestamp())),
	                     record.key(),
	                     record.value());
	             offset.acknowledge();
	             latch.countDown();
	         }).dispose();
			
			
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
