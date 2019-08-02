package kr.co.wishDream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableConfigurationProperties
@EnableR2dbcRepositories
public class TestDatabaseConfig extends AbstractR2dbcConfiguration {
	
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

	@Bean
	public ConnectionFactory connectionFactory() {
		return new PostgresqlConnectionFactory(
				PostgresqlConnectionConfiguration.builder()
				.host(DB_HOST)
				.port(DB_PORT)
				.database(DB_DATABASE)
				.username(DB_USER)
				.password(DB_PASSWORD)
				.build());
	}
	

	/*
	@Bean
	public MemberRepository memberRepository(R2dbcRepositoryFactory factory) {
		return factory.getRepository(MemberRepository.class);
	}*/

}
