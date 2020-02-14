package kr.co.wishDream.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.repository.MemberRepository;
import kr.co.wishDream.service.MemberService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	
	@Override
	public Mono<Member> findOneByEmail(String email) {
		return memberRepository.findByEmail(email);
	}


	@Override
	public Flux<?> getMenu() {
		Mono<SecurityContext> securityContext = ReactiveSecurityContextHolder.getContext();
		
		
		
		return securityContext.map(SecurityContext::getAuthentication)
			.map(Authentication::getPrincipal)
			.flux();
	}

}
