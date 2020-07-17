package kr.co.wishDream.service.impl;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import io.reactivex.Flowable;
import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.Menu;
import kr.co.wishDream.repository.MemberRepository;
import kr.co.wishDream.service.MemberService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	
	private Logger LOG = LoggerFactory.getLogger(MemberServiceImpl.class);
//	private Menu

	
	@Override
	public Mono<Member> findOneByEmail(String email) {
		return memberRepository.findByEmail(email);
	}


	@Override
	public Flux<Menu> getMenu(Mono<? extends Principal> user) throws Exception {
		return memberRepository.findByAuthMenu(user);
	}

}
