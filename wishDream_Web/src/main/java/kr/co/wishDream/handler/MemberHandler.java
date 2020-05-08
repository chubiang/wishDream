package kr.co.wishDream.handler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.Menu;
import kr.co.wishDream.service.MemberService;
import reactor.core.publisher.Mono;

@Component
public class MemberHandler {
	
	private Logger LOG = LoggerFactory.getLogger(MemberHandler.class);
	
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
	
	public Mono<ServerResponse> login(ServerRequest request) {
		return ServerResponse
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(ReactiveSecurityContextHolder.getContext()
					.map(SecurityContext::getAuthentication)
					.map(Authentication::getName), Member.class);
	}
	
	public Mono<ServerResponse> getMenus(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(memberService.getMenu(), Menu.class);
	}
	
	public Mono<ServerResponse> username(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(ReactiveSecurityContextHolder.getContext()
						.map(SecurityContext::getAuthentication)
						.map(Authentication::getName), Object.class);
	}

}
