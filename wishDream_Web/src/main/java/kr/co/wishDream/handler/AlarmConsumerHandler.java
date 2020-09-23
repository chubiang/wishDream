package kr.co.wishDream.handler;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Flowable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

@Component
public class AlarmConsumerHandler implements WebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AlarmConsumerHandler.class);
	
	@Value("${kafka.topics}") 
	String[] topics;
	
	@Autowired
	@Qualifier("reactiveKafkaReceiver")
	Flux<ReceiverRecord<String, String>> reactiveKafkaReceiver;
	
	@Autowired
	ReceiverOptions<String, String> receiverOptions;
	
	private ObjectMapper objectMapper;
	private SimpleDateFormat dateFormat;
	
	public String emitMessageSession() {
		Collection<String> listTopics = receiverOptions.subscriptionTopics();
		for (String topic : listTopics) {
			System.out.println("## topic = "+ topic);
		}
		// 메세지를 왜 안찍는것인가..............
		reactiveKafkaReceiver.doOnNext(r -> {
			 System.out.println("나를 찾아줘....!: "+r.value());
             r.receiverOffset().acknowledge();
             r.receiverOffset().commit().block();
		}).subscribe();
		return reactiveKafkaReceiver.log().toString();
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session.send(Flowable.just(session.textMessage(emitMessageSession())));
	}
}
