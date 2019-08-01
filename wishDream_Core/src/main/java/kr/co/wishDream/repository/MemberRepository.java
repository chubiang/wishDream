package kr.co.wishDream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import kr.co.wishDream.domain.Member;

@Repository
public interface MemberRepository extends ReactiveCrudRepository<Member, Integer> {
	
}
