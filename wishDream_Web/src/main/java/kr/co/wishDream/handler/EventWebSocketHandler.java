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

import kr.co.wishDream.domain.NoticeMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EventWebSocketHandler implements WebSocketHandler {
	private Logger LOG = LoggerFactory.getLogger(EventWebSocketHandler.class);
	
	private ObjectMapper objectMapper;
	
	
	public Mono<ServerResponse> emitUserMessages() throws JsonProcessingException {
		ArrayList<NoticeMessage> msgs = new ArrayList<>();

		msgs.add(new NoticeMessage("안녕", "반가워용!", 1L, 2L, "블랙", "오렌지", 2L, 1, null));
		msgs.add(new NoticeMessage("안녕@", "반가워용!##", 1L, 2L, "화이트", "레몬", 2L, 2, null));
		msgs.add(new NoticeMessage("안녕$", "반가워용$$!", 1L, 2L, "연두", "보라", 2L, 3, null));
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(msgs), ArrayList.class);
	}

	public String emitUserMessagesByJson() throws JsonProcessingException {
		ArrayList<NoticeMessage> msgs = new ArrayList<>();

		msgs.add(new NoticeMessage("안녕", "반가워용!", 1L, 2L, "블랙", "오렌지", 2L, 1, null));
		msgs.add(new NoticeMessage("안녕@", "반가워용!##", 1L, 2L, "화이트", "레몬", 2L, 2, null));
		msgs.add(new NoticeMessage("안녕$", "반가워용$$!", 1L, 2L, "연두", "보라", 2L, 3, null));
		
		objectMapper = new ObjectMapper();
		
		return objectMapper.writeValueAsString(msgs);
	}
	
	private Flux<String> toMessage() {
		final String msg = "echo ";
		return Flux.just(msg);
	}
	

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		
		try {
			return session.send(
						Mono.just(session.textMessage(emitUserMessagesByJson()))
					)
					.then();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return session.send(Flux.interval(Duration.ofSeconds(1))
//				.zipWith(toMessage(), (x, y) -> x + y)
//				.map(session::textMessage)
//				);
		return null;
	}
	
}
