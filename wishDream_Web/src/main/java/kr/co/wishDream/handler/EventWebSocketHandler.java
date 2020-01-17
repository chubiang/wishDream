package kr.co.wishDream.handler;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.domain.NoticeMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EventWebSocketHandler {
	private Logger LOG = LoggerFactory.getLogger(EventWebSocketHandler.class);
	
	
	public Mono<ServerResponse> emitUserMessages(ServerRequest request) {
		ArrayList<NoticeMessage> msgs = new ArrayList<>();

		msgs.add(new NoticeMessage("안녕", "반가워용!", 1L, 2L, "블랙", "오렌지", 2L, 3, null));
		msgs.add(new NoticeMessage("안녕@", "반가워용!##", 1L, 2L, "화이트", "레몬", 2L, 3, null));
		msgs.add(new NoticeMessage("안녕$", "반가워용$$!", 1L, 2L, "연두", "보라", 2L, 3, null));
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Flux.fromIterable(msgs), NoticeMessage.class);
	}
}
