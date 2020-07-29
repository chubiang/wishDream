package kr.co.wishDream.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.co.wishDream.handler.AlarmWebSocketHandler;
import kr.co.wishDream.handler.KafkaMessageHandler;

@Configuration
public class EventRouter {
	
	@Bean
	public RouterFunction<?> eventRoutes(AlarmWebSocketHandler handler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/alarmList")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
						request -> {
								try {
									return handler.emitUserMessages();
								} catch (JsonProcessingException e) {
									e.printStackTrace();
								}
								return null;
						});
	}
	
}
