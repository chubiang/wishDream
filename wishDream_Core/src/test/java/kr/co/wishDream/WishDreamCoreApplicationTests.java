package kr.co.wishDream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplicationWebFluxConfig.class},
				properties = {"classpath:application-test.yml"})
public class WishDreamCoreApplicationTests {
	
	private ConnectionFactory connectionFactory =  ConnectionFactories.get("r2dbc:postgresql://wishDream:wishDream@localhost:5430/wishDream");
	
	private DatabaseClient client = DatabaseClient.create(connectionFactory);

	
	
	@Test
	public void contextLoads() {
		
		Mono<Member> member = client.select()
				.from(Member.class)
				.matching(Criteria.where("email").is("nana"))
				.as(Member.class).one();
		
		System.out.println(member.toString());
		
		
	}

}
