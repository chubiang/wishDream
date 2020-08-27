package kr.co.wishDream.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
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

import kr.co.wishDream.domain.NoticeMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

@Component
public class AlarmProducerHandler {
	private Logger LOG = LoggerFactory.getLogger(AlarmProducerHandler.class);

	@Autowired
	private KafkaSender<Integer, String> kafkaSender;
	
	@Value("${kafka.server}")
	String kafkaServer;
	
	@Value("${kafka.topics}") 
	List<String> topics;
	
	private ObjectMapper objectMapper;
	
	public void sendMessage() {
		ArrayList<NoticeMessage> msgs = noticeMessages();
		
		kafkaSender.send(Flux.empty()
				.map(x -> {
					Map<Integer, NoticeMessage> msgMap = new LinkedHashMap<Integer, NoticeMessage>();
	
					for (int i = 0; i < msgs.size(); i++) {
						msgMap.put(i+1, msgs.get(i));
					}
					String message = null;
					try {
						message = objectMapper.writeValueAsString(msgMap);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					return SenderRecord.create(
							new ProducerRecord<Integer, String>(
									topics.get(0), 
									1, 
									1, 
									message //value??
									), 1);
				})
				.doOnError(onError -> LOG.error("## KAFKA MESSAGE PRODUCER ## SUBSCRIBE MESSAGE"+onError.getStackTrace()))
				);
	}
	
	public void close() {
		kafkaSender.close();
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
}
