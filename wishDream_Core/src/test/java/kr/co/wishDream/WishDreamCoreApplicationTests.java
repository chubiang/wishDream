package kr.co.wishDream;

import org.davidmoten.rx.jdbc.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.wishDream.domain.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationWebFluxConfig.class,
					 TestDatabaseConnect.class})
public class WishDreamCoreApplicationTests {
	
	@Autowired
	private TestDatabaseConnect testDatabaseConnect;
	
	private Database database;

	@Test
	public void contextLoads() throws Exception {
		findByEmail("nana@gmail.com");
	}

	public void findByEmail(String email) throws Exception {
		this.setDatabase(Database.from(testDatabaseConnect.pool()));
		
		String sql = "SELECT * FROM member WHERE EMAIL = ?";
		
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
							
							return member;
						})
				.blockingSubscribe(System.out::println);
		
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
}
