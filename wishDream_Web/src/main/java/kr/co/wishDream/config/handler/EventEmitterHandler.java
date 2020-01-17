package kr.co.wishDream.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EventEmitterHandler implements WebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(EventEmitterHandler.class);
	
	@Override
	public Mono<Void> handle(WebSocketSession session) {
		Flux<WebSocketMessage> messages = session.receive()
				.doOnNext(message -> {
					message.getPayloadAsText();
				})
				.doOnSubscribe(sub -> LOG.info("CONNECT // "+ session.getId()))
				.doFinally(sig -> LOG.info("DISCONNECT // "+ session.getId()));
		return session.send(messages);
	}
	
}
