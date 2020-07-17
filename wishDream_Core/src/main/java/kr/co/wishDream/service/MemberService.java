package kr.co.wishDream.service;

import java.security.Principal;

import io.reactivex.Flowable;
import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.Menu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemberService {

	public Mono<Member> findOneByEmail(String email);
	
	Flux<Menu> getMenu(Mono<? extends Principal> mono) throws Exception;

}
