package kr.co.wishDream.handler;

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
	
	@Autowired
	private MemberService memberService;
	
	public Mono<ServerResponse> findByUserName(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(memberService.findOneByEmail(request.pathVariable("email")), Member.class);
	}
}
