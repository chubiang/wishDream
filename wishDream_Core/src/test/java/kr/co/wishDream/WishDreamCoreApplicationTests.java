package kr.co.wishDream;

import org.davidmoten.rx.jdbc.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.reactivex.Flowable;
import kr.co.wishDream.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDatabaseConnect.class})
public class WishDreamCoreApplicationTests {
	
	@Autowired
	private TestDatabaseConnect testDatabaseConnect;
	
	private Database db;
	
	@Test
	public void contextLoads() throws Exception {
		Flowable<Member> mem = findByEmail("nana@gmail.com");
		mem.subscribe(System.out::println);
		
	}

	public Flowable<Member> findByEmail(String email) throws Exception {
		this.db = testDatabaseConnect.database();
		
		String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
		
		Flowable<Member> memberFlowable =
				db.select(sql)
					.parameter(email)
					.get(
						rs -> {
							Member member = new Member();
							member.setEmail(rs.getString("email"));
							member.setBirth(rs.getDate("birth"));
							member.setJoinDate(rs.getDate("joinDate"));
							member.setLeaveDate(rs.getDate("leaveDate"));
							member.setEtc(rs.getString("etc"));
							
							return member;
						});
			
		return memberFlowable;
		
	}
}
