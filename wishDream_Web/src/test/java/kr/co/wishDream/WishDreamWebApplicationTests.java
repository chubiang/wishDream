package kr.co.wishDream;

import org.davidmoten.rx.jdbc.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.reactivex.Flowable;
import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDatabaseConnect.class})
@TestPropertySource(locations = {"classpath:application.yml"})
public class WishDreamWebApplicationTests {
	
	@Autowired
	private TestDatabaseConnect testDatabaseConnect;
	
	private Database database;
	
	Mono<Member> member;

	@Test
	public void contextLoads() throws Exception {
		String email = "nana@gmail.com";
		
//		Flowable<Member> mem = findByEmailAutoMap(email);
//		member = Mono.justOrEmpty(mem.toObservable().blockingSingle());
//		System.out.print("mem = ");
//		member.subscribe(x -> System.out.println(x));
		
		System.out.println(findByUsername(email).blockOptional().get().toString());
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
