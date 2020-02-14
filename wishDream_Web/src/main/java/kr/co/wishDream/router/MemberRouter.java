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
	RouterFunction<ServerResponse> memberRoutes(MemberHandler handler) {
		
		return RouterFunctions
				.route(GET("/member/{email}")
				.and(accept(MediaType.APPLICATION_JSON)), request -> {
					try {
						return handler.findByUserName(request);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				})
				.andRoute(GET("/menu")
				.and(accept(MediaType.APPLICATION_JSON)), request -> {
					try {
						return handler.getMenus(request);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				});
	}
}