package kr.co.wishDream.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.handler.MemberHandler;


@Configuration
public class MemberRouter {

	@Bean
	RouterFunction<ServerResponse> memberRoutes(MemberHandler handler) throws Exception{
		
		return RouterFunctions
				.route(GET("/oauth2/registration")
				.and(accept(MediaType.APPLICATION_JSON)), request -> {
						return handler.sendOAuth2Registrations();
				})
				.andRoute(GET("/member/{email}")
					.and(accept(MediaType.APPLICATION_JSON)), request -> {
						return handler.findByUserName(request);
				})
				.andRoute(GET("/menu")
					.and(accept(MediaType.APPLICATION_JSON)), request -> {
						return handler.getMenus(request);
				});
	}
}