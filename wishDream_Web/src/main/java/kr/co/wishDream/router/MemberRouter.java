package kr.co.wishDream.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import kr.co.wishDream.handler.MemberHandler;

@Configuration
public class MemberRouter {

	@Bean
	RouterFunction<?> memberRoutes(MemberHandler handler) {
		return RouterFunctions.route(GET("/member/{name}").and(accept(APPLICATION_JSON)), handler::findByUserName);
	}
}
