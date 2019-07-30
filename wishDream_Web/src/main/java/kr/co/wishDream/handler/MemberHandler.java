package kr.co.wishDream.handler;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@Component
public class MemberHandler {
	
	public Mono<ServerResponse> findByUserName(ServerRequest request) {
		final Mono<Member> member = Mono.just(new Member("nana", "nana@gmail.com", "My name is nana.", new Date(), new Date(), null));
		System.out.println(member.toString());
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(member, Member.class);
	}
}
