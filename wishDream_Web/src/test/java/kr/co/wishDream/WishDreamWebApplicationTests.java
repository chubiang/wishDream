package kr.co.wishDream;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.davidmoten.rx.jdbc.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.reactivex.Flowable;
import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WishDreamWebApplicationTests {
	
	@Autowired
	private TestDatabaseConnect testDatabaseConnect;
	
	@Autowired
	OAuth2ClientProperties properties;
	
	private Database database;
	
	Mono<Member> member;

	@Autowired
	InMemoryReactiveClientRegistrationRepository clientRegistrationRepository;
	
	@Test
	public void contextLoads() throws Exception {
		String email = "nana@gmail.com";
		List<ClientRegistration> registrations = StreamSupport.stream(clientRegistrationRepository.spliterator(), true)
				.collect(Collectors.toList());
		registrations.forEach(System.out::println);
//		Pattern urlMatcher = Pattern.compile("(login)|(\\/)*[!@#$%^&*(),.?\\\\\"~`:{}|<>+=_-]*");
//		System.out.println(urlMatcher.matcher("/fdsfddd.ddd.ffffffffffff&%$##@$()+=-_\"\\~`").find());
//		Flowable<Member> mem = findByEmailAutoMap(email);
//		member = Mono.justOrEmpty(mem.toObservable().blockingSingle());
//		System.out.print("mem = ");
//		member.subscribe(x -> System.out.println(x));
		
//		System.out.println(findByUsername(email).blockOptional().get().toString());
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String password = encoder.encode("123");
//		System.out.println(password);
		
//		System.out.println(System.getProperty("java.version"));
	}
	
	public Mono<UserDetails> findByUsername(String email) {
		return findByEmailGetMono(email).switchIfEmpty(Mono.defer(() -> {
			return Mono.error(new UsernameNotFoundException("User not found"));
		}) );
	}
	
	public Mono<UserDetails> findByEmailGetMono(String email) {
		Flowable<UserDetails> memberFlowable = null;
		try {
			this.setDatabase(Database.from(testDatabaseConnect.pool()));
			
			String sql = "SELECT * FROM member WHERE EMAIL = ?";
	
			memberFlowable =
					database.select(sql)
						.parameter(email)
						.get(
							rs -> {
								Member member = new Member();
								member.setUsername(rs.getString("username"));
								member.setEmail(rs.getString("email"));
								member.setBirth(rs.getDate("birth"));
								member.setJoinDate(rs.getDate("joinDate"));
								member.setLeaveDate(rs.getDate("leaveDate"));
								member.setEtc(rs.getString("etc"));
								
								return (UserDetails) member;
						});
		} catch (Exception e) {
			e.getStackTrace();
		}
		return Mono.from(memberFlowable);
	}
	
	public Flowable<Member> findByEmailAutoMap(String email) throws Exception {
		this.setDatabase(Database.from(testDatabaseConnect.pool()));
		
		return database.select("SELECT * FROM member WHERE EMAIL = ?")
				.parameter(email)
				.getAs(Member.class);
	}
	
	
	public void setDatabase(Database database) {
		this.database = database;
	}

}
