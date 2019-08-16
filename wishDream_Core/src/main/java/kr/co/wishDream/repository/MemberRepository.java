package kr.co.wishDream.repository;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.reactivex.Flowable;
import kr.co.wishDream.DatabaseConnect;
import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@Component
public class MemberRepository {

	private Database db;
	
	@Autowired
	private DatabaseConnect databaseConnect;
	
	public MemberRepository() throws Exception {
		this.db = databaseConnect.database();
	}
	
	Mono<Member> findByEmail(String email) {
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
					
		return Mono.from(memberFlowable);
		
	}
	
}
