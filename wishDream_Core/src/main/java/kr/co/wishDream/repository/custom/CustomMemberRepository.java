package kr.co.wishDream.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.query.Criteria;
import org.springframework.stereotype.Repository;

import io.r2dbc.spi.ConnectionFactory;
import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@Repository
public class CustomMemberRepository{
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	DatabaseClient client = DatabaseClient.create(connectionFactory);
	
	
	public Mono<Member> getMember(String email) {
		return client.select()
			.from(Member.class)
			.matching(Criteria.where("email").is(email))
			.as(Member.class).one();
	}
}
