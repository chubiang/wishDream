package kr.co.wishDream.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import kr.co.wishDream.domain.Member;
import reactor.core.publisher.Mono;

@Component
public class MemberRepository {
	
	private ConnectionFactory connectionFactory;
	
	@Value("${wishDream.database.driver:postgresql}")
	private String DB_DRIVER;
	@Value("${wishDream.database.host:localhost}")
	private String DB_HOST;
	@Value("${wishDream.database.port:5430}")
	private Integer DB_PORT;
	@Value("${wishDream.database.database:wishDream}")
	private String DB_DATABASE;
	@Value("${wishDream.database.user:wishDream}")
	private String DB_USER;
	@Value("${wishDream.database.password:wishDream}")
	private String DB_PASSWORD;
	
	
	public MemberRepository() throws Exception {
		this.connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
				   .option(ConnectionFactoryOptions.DRIVER, DB_DRIVER)
				   .option(ConnectionFactoryOptions.HOST, DB_HOST)
				   .option(ConnectionFactoryOptions.PORT, DB_PORT)  // optional, defaults to 5432
				   .option(ConnectionFactoryOptions.USER, DB_USER)
				   .option(ConnectionFactoryOptions.PASSWORD, DB_PASSWORD)
				   .option(ConnectionFactoryOptions.DATABASE, DB_DATABASE)  // optional
				   .option(ConnectionFactoryOptions.SSL, true)
				   .build());

	}
	
	Mono<Member> getMember(String email) {
		
		return null;
	}
}
