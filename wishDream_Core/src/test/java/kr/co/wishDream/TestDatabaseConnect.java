package kr.co.wishDream;

import java.util.concurrent.TimeUnit;

import org.davidmoten.rx.jdbc.ConnectionProvider;
import org.davidmoten.rx.jdbc.pool.DatabaseType;
import org.davidmoten.rx.jdbc.pool.NonBlockingConnectionPool;
import org.davidmoten.rx.jdbc.pool.Pools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-test.yml")
public class TestDatabaseConnect {

	@Value("${wishDream.database.url:jdbc:postgresql://localhost:5430/wishDream}")
	private String DB_URL;
	@Value("${wishDream.database.host:localhost}")
	private String DB_HOST;
	@Value("${wishDream.database.port:5430}")
	private Integer DB_PORT;
	@Value("${wishDream.database.database:wishDream}")
	private String DB_DATABASE;
	@Value("${wishDream.database.user:wishdream}")
	private String DB_USER;
	@Value("${wishDream.database.password:wishdream}")
	private String DB_PASSWORD;

	public NonBlockingConnectionPool pool() throws Exception{
		return Pools
				  .nonBlocking()
				  // the jdbc url of the connections to be placed in the pool
				  .connectionProvider(ConnectionProvider.from(DB_URL, DB_USER, DB_PASSWORD))
				  // an unused connection will be closed after thirty minutes
				  .maxIdleTime(30, TimeUnit.MINUTES)
				  // connections are checked for healthiness on checkout if the connection
				  // has been idle for at least 5 seconds
				  .healthCheck(DatabaseType.POSTGRES)
				  .idleTimeBeforeHealthCheck(5, TimeUnit.SECONDS)
				  // if a connection fails creation then retry after 30 seconds
				  .connectionRetryInterval(30, TimeUnit.SECONDS)
				  // the maximum number of connections in the pool
				  .maxPoolSize(3)
				  .build();
	}
}
