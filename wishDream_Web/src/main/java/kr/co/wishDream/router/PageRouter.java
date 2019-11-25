package kr.co.wishDream.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PageRouter {
	
	@Bean
	public RouterFunction<?> viewRoutes() {
		return RouterFunctions
				.route(RequestPredicates.GET("/login"),
						req -> ServerResponse
								.ok()
								.render("login",
										req.exchange().getAttributes())
				)
				.andRoute(RequestPredicates.GET("/logout"),
						req -> ServerResponse.ok().render("login"));
				
	}
}
