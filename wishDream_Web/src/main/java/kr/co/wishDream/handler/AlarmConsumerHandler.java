package kr.co.wishDream.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.wishDream.config.KafkaConfig;
import kr.co.wishDream.domain.NoticeMessage;
import kr.co.wishDream.service.KafkaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverRecord;

@Component("alarmConsumerHandler")
public class AlarmConsumerHandler  {

	private static final Logger LOG = LoggerFactory.getLogger(AlarmConsumerHandler.class);
	
	@Autowired
	KafkaService kafkaService;
	
	@Value("${kafka.topics}") 
	String[] topics;
	
	@Autowired
	KafkaConfig kafkaConfig;

	@Autowired
	@Qualifier("reactiveKafkaReceiver")
	Flux<ConsumerRecord<String, String>> reactiveKafkaReceiver;
	
	private ObjectMapper objectMapper;
	private SimpleDateFormat dateFormat;
	
	public Mono<ServerResponse> emitMessage() {
//		try {
//			Flux<ReceiverRecord<Integer, String>> reactiveKafkaReceiver = 
//					KafkaReceiver.create(receiverOptions).receive();
			
//			String message = objectMapper.writeValueAsString(
//					reactiveKafkaReceiver.receive()
//						.doOnNext(onNext -> LOG.info("## KAFKA-RECEIVER ## SUBSCRIBE MESSAGE ="+onNext.value()))
//						.doOnError(onError -> LOG.error("## KAFKA MESSAGE ## SUBSCRIBE MESSAGE = "+onError.getStackTrace()))
//						.map(record -> { return record.value(); }).collectList().block()
//					);
			return ServerResponse
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(reactiveKafkaReceiver
							.doOnSubscribe(onNext -> LOG.info("## KAFKA-RECEIVER ## SUBSCRIBE MESSAGE ="+onNext))
							.doOnError(onError -> LOG.error("## KAFKA MESSAGE ## SUBSCRIBE MESSAGE = "+onError.getCause()))
							, ConsumerRecord.class);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
	}
	
	public String emitMessageSession() {
		int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
		dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
//		try {
//			reactiveKafkaReceiver.subscribe(record -> {
//	             ReceiverOffset offset = record.receiverOffset();
//	             System.out.printf("Received message: topic-partition=%s offset=%d timestamp=%s key=%d value=%s\n",
//	                     offset.topicPartition(),
//	                     offset.offset(),
//	                     dateFormat.format(new Date(record.timestamp())),
//	                     record.key(),
//	                     record.value());
//	             offset.acknowledge();
//	             latch.countDown();
//	         }).dispose();
			
//			receiver
//				.doOnNext(onNext -> LOG.info("## KAFKA-RECEIVER ## SUBSCRIBE MESSAGE ="+onNext.value()))
//				.doOnError(onError -> LOG.error("## KAFKA MESSAGE ## SUBSCRIBE MESSAGE = "+onError.getStackTrace()))
//				.map(record -> {
//					LOG.debug("## record = " + record.value() );
//					return record.value(); 
//				}).publish();

//			return objectMapper.writeValueAsString(
//					receiver
//						.doOnNext(onNext -> LOG.info("## KAFKA-RECEIVER ## SUBSCRIBE MESSAGE ="+onNext.value()))
//						.doOnError(onError -> LOG.error("## KAFKA MESSAGE ## SUBSCRIBE MESSAGE = "+onError.getStackTrace()))
//						.map(record -> {
//							LOG.debug("## record = " + record.value() );
//							return record.value(); 
//						}).publish()
//					);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
		return "";
	}

//	@Override
//	public Mono<Void> handle(WebSocketSession session) {
//		return session.send((Publisher<WebSocketMessage>) Flowable.just(session.textMessage(emitMessageSession()))).then();
//	}
}
