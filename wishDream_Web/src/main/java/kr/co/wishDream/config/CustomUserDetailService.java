package kr.co.wishDream.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kr.co.wishDream.repository.MemberRepository;
import reactor.core.publisher.Mono;

@Component
public class CustomUserDetailService implements ReactiveUserDetailsService {

	@Autowired
	MemberRepository memberRepository;

	@Override
	public Mono<UserDetails> findByUsername(String email) {
		return memberRepository.findByEmailToUserDetails(email)
				.switchIfEmpty(Mono.defer(() -> {
					return Mono.error(new UsernameNotFoundException("User not found"));
				}));
	}
}
