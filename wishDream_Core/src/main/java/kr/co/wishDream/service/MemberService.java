package kr.co.wishDream.service;

import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

public interface MemberService {

	public Mono<Member> findOneByEmail(String email);
}
