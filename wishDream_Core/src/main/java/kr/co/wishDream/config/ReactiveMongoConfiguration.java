package kr.co.wishDream.config;

import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(basePackages = {"kr.co.wishDream.repository"})
@Configuration
public class ReactiveMongoConfiguration extends MongoReactiveAutoConfiguration{

	@Bean
	public ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory databaseFactory) {
		return new ReactiveMongoTransactionManager(databaseFactory);
	}
	
	public ReactiveMongoTemplate mongoTemplate(ReactiveMongoDatabaseFactory databaseFactory, MongoConverter converter) {
		return new ReactiveMongoTemplate(databaseFactory, converter);
	}
	
	
}
