package kr.co.wishDream.repository;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.reactivex.Flowable;
import io.reactivex.Single;
import kr.co.wishDream.DatabaseConnect;
import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@Component
public class MemberRepository {
	
	@Autowired
	private DatabaseConnect databaseConnect;

	private Database database;
	
	public Mono<Member> findByEmailAutoMap() {
		Single<Member> mem = database.select("SELECT * FROM member WHERE EMAIL = ?")
				.autoMap(Member.class).singleOrError();
		return Mono.from(mem.toFlowable());
	}
	
	
	public Mono<Member> findByEmail(String email) {
		Flowable<Member> memberFlowable = null;
		try {
			this.setDatabase(Database.from(databaseConnect.pool()));
			
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
								
								return member;
						});
		} catch (Exception e) {
			e.getStackTrace();
		}
		return Mono.from(memberFlowable);
	}


	public void setDatabase(Database database) {
		this.database = database;
	}
	
}
