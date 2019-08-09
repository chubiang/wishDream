package kr.co.wishDream.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.repository.MemberRepository;
import kr.co.wishDream.service.MemberService;
import reactor.core.publisher.Mono;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repository;

	
	@Override
	public Mono<Member> findOneByEmail(String email) {
		return null;
	}

}
