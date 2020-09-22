package kr.co.wishDream.handler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Flowable;
import kr.co.wishDream.config.KafkaConfig;
import kr.co.wishDream.domain.NoticeMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component("alarmProducerHandler")
public class AlarmProducerHandler implements WebSocketHandler{
	private Logger LOG = LoggerFactory.getLogger(AlarmProducerHandler.class);

	@Autowired
	KafkaSender<String, String> reactiveKafkaSender;
	@Value("${kafka.server}")
	String kafkaServer;
	
	@Value("${kafka.topics}") 
	List<String> topics;
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void sendMessage() {
		ArrayList<NoticeMessage> msgs = noticeMessages();
		
		Map<Integer, NoticeMessage> msgMap = new LinkedHashMap<Integer, NoticeMessage>();
		
		for (int i = 0; i < msgs.size(); i++) {
			msgMap.put(i+1, msgs.get(i));
		}
		String message = null;
		try {
			message = objectMapper.writeValueAsString(msgMap);
			LOG.info("## message = " + message);
			LOG.info("## topics.get(0) = " + topics.get(0));
			SenderRecord<String, String, Integer> msg = SenderRecord.create(
					new ProducerRecord<String, String>(
							topics.get(0), 
							"1", 
							message //value??
							), 1);
			reactiveKafkaSender.send(Flowable.just(msg))
				.doOnError(onError -> LOG.error("SEND FAILED", onError));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} finally {
			LOG.info("## KAFKA SENDER - CLOSE()");
			reactiveKafkaSender.close();
		}
	}
	
	// temporary method
	public ArrayList<NoticeMessage> noticeMessages() {
		ArrayList<NoticeMessage> msgs = new ArrayList<>();

		msgs.add(new NoticeMessage("Hello", "message!", 1, 2, "choco", "doo", 2, 1, null));
		msgs.add(new NoticeMessage("Hello@", "message!##", 1, 2, "baba", "rain", 2, 2, null));
		msgs.add(new NoticeMessage("Hello$", "message$$!", 1, 2, "poo", "biscuit", 2, 3, null));
		return msgs;
	}

	public String emitUserMessagesByJson() throws JsonProcessingException {
		ArrayList<NoticeMessage> msgs = new ArrayList<>();

		msgs.add(new NoticeMessage("Hello", "message!", 1, 2, "choco", "doo", 2, 1, null));
		msgs.add(new NoticeMessage("Hello@", "message!##", 1, 2, "baba", "rain", 2, 2, null));
		msgs.add(new NoticeMessage("Hello$", "message$$!", 1, 2, "poo", "biscuit", 2, 3, null));
		
		objectMapper = new ObjectMapper();
		
		return objectMapper.writeValueAsString(msgs);
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		
		Flux<WebSocketMessage> messages = session.receive()
				.doOnNext(message -> {
					message.getPayloadAsText();
					LOG.info("OnNEXT // "+ session.getId());
				})
				.doFinally(sig -> LOG.info("FINALLY // "+ session.getId()));
		return session.send(messages);
	}
}
