package kr.co.wishDream.handler;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Flowable;
import kr.co.wishDream.config.KafkaConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

@Component
public class AlarmConsumerHandler implements WebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AlarmConsumerHandler.class);
	
	@Value("${kafka.topics}") 
	String[] topics;
	
	@Autowired
	KafkaConfig kafkaConfig;

	@Autowired
	@Qualifier("reactiveKafkaReceiver")
	KafkaReceiver<String, String> reactiveKafkaReceiver;
	
	@Autowired
	ReceiverOptions<String, String> receiverOptions;
	
	private ObjectMapper objectMapper;
	private SimpleDateFormat dateFormat;
	
	@PostConstruct
	public void receiveConsumer() {
		reactiveKafkaReceiver.receive()
		.doOnError(onError -> LOG.error("## KAFKA MESSAGE ## ERROR MESSAGE = ", onError))
		.subscribe(c -> {
			System.out.println("## receive = "+c.toString());
		});
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
		
		Collection<String> listTopics = receiverOptions.subscriptionTopics();
		for (String topic : listTopics) {
			System.out.println("topic = "+ topic);
		}
		
		return "";
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.send((Publisher<WebSocketMessage>) Flowable.just(session.textMessage(emitMessageSession()))).then();
	}
}
