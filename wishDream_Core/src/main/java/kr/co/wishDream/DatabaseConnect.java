package kr.co.wishDream;

import java.util.concurrent.TimeUnit;

import org.davidmoten.rx.jdbc.ConnectionProvider;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.pool.DatabaseType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.yml")
public class DatabaseConnect {

	@Value("${wishDream.database.url:wishDream}")
	private String DB_URL;
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

	@Bean(destroyMethod = "destory")
	public Database database() throws Exception{
		return Database
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
