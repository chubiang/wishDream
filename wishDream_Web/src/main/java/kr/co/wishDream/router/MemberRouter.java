package kr.co.wishDream.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

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
				.route(GET("/member/{email}")
					.and(accept(MediaType.APPLICATION_JSON)), request -> {
						return handler.findByUserName(request);
				})
				.andRoute(GET("/menu")
					.and(accept(MediaType.APPLICATION_JSON)), request -> {
						return handler.getMenus(request);
				})
				.andRoute(GET("/username")
					.and(accept(MediaType.APPLICATION_JSON)), request -> {
						return handler.username(request);
				})
				.andRoute(POST("/join")
					.and(contentType(MediaType.APPLICATION_FORM_URLENCODED)), request -> {
					return handler.signUp(request);
				});
	}
}