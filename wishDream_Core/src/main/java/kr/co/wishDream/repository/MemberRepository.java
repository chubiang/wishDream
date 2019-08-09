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
	private DatabaseConnect connect;
	
	public MemberRepository() throws Exception {
		this.db = connect.database();
	}
	
	Mono<Member> findByEmail(String email) {
		String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
		/*
		Flowable<Member> memberFlowable =
				db.select(sql)
					.parameter(email)
					.get(
						rs -> {
							Member member = new Member();
						});
						*/
		return null;
		
	}
	
}
