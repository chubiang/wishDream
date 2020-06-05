package kr.co.wishDream.handler;

import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.Menu;
import kr.co.wishDream.repository.MemberRepository;
import kr.co.wishDream.service.MemberService;
import reactor.core.publisher.Mono;

@Component
public class MemberHandler {
	
	private Logger LOG = LoggerFactory.getLogger(MemberHandler.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepository;
	
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

	public Mono<ServerResponse> signUp(ServerRequest request) throws Exception {
//		MultiValueMap<String, String> formData = request.formData().map(data-> {
//			MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//			data.entrySet().forEach(entryData -> {
//				LOG.info(entryData.getKey()+"="+entryData.getValue());
//				formData.addAll(entryData.getKey(), entryData.getValue());
//			});
//			return Mono.just(data)formData;
//		});
//		if (formData.isEmpty()) {
//			return ServerResponse.status(HttpStatus.BAD_REQUEST)
//					.body(Mono.error(new NullPointerException("No Receive Form Data")), Object.class);
//		}
		return ServerResponse.ok()
				.body(memberRepository.signUp(formData), Object.class);
		
	}

}
