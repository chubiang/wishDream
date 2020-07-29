package kr.co.wishDream.config.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.wishDream.domain.NoticeMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
public class CustomAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler{

	private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	private KafkaSender<Integer, String> kafkaSender;
	
	@Value("${kafka.topics}") 
	List<String> topics;
	
	private ObjectMapper objectMapper;
	
	// temporary method
	public ArrayList<NoticeMessage> noticeMessages() {
		ArrayList<NoticeMessage> msgs = new ArrayList<>();

		msgs.add(new NoticeMessage("Hello", "message!", 1, 2, "choco", "doo", 2, 1, null));
		msgs.add(new NoticeMessage("Hello@", "message!##", 1, 2, "baba", "rain", 2, 2, null));
		msgs.add(new NoticeMessage("Hello$", "message$$!", 1, 2, "poo", "biscuit", 2, 3, null));
		return msgs;
	}
	
	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
		ArrayList<NoticeMessage> msgs =  noticeMessages();
		objectMapper = new ObjectMapper();
		// temporary
			kafkaSender.send(Flux.just(msgs)
					.map(x -> {
						Map<Integer, NoticeMessage> msgMap = new LinkedHashMap<Integer, NoticeMessage>();
						
						for (int i = 0; i < msgs.size(); i++) {
							Integer index = i;
						}
							return SenderRecord.create(
								new ProducerRecord<Integer, String>(
										topics.get(0), 
										1, 
										1, 
										msgMap.toString() //value??
									), 1);
						})
					.doOnError(onError -> LOG.error("## KAFKA MESSAGE PRODUCER ## SUBSCRIBE MESSAGE"+onError.getStackTrace()))
					);
		
		ServerWebExchange exchange = webFilterExchange.getExchange();
		return webFilterExchange.getChain().filter(exchange).doOnSuccess(ok -> {
			LOG.info("[Security] Authentication Success ==== " + authentication);
		});
	}

}
