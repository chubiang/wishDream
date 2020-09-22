package kr.co.wishDream.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.co.wishDream.handler.AlarmConsumerHandler;
import kr.co.wishDream.handler.AlarmWebSocketHandler;

@Configuration
public class KafkaEventRouter {
	
	@Bean
	public RouterFunction<?> eventRoutes(AlarmConsumerHandler handler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/kafkaAlarm")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
						request -> {
//								return handler.emitMessage();
							return null;
						});
	}
	
}
