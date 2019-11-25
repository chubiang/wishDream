package kr.co.wishDream.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.service.MemberService;
import reactor.core.publisher.Mono;

@Component
public class MemberHandler {
	
	private Logger log = LoggerFactory.getLogger(MemberHandler.class);

	
	@Autowired
	private MemberService memberService;
	
	public Mono<ServerResponse> findByUserName(ServerRequest request) {
		Mono<String> fallback = Mono.error(new Throwable("Not exsit member"));
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(memberService.findOneByEmail(
						request
						.pathVariable("email")), Member.class
					).onErrorResume(e-> ServerResponse
											.ok()
											.contentType(MediaType.APPLICATION_JSON)
											.bodyValue(fallback));
	}
}
