package kr.co.wishDream.service;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.Menu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemberService {

	public Mono<Member> findOneByEmail(String email);
	
	public Flux<?> getMenu();
}
