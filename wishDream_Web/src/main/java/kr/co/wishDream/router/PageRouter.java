package kr.co.wishDream.router;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.handler.MemberHandler;

@Configuration
public class PageRouter {
	
	@Autowired
	MemberHandler memberHandler;
	
	@Bean
	public RouterFunction<?> viewRoutes() {
		return RouterFunctions
				.route(RequestPredicates.GET("/login"),
						req -> ServerResponse
								.ok()
								.render("login",
										req.exchange().getAttributes())
				)
				.andRoute(RequestPredicates.POST("/login"),
						req -> memberHandler.login(req)
				)
				.andRoute(RequestPredicates.GET("/login/oauth2/code/kakao"),
						req -> ServerResponse
								.temporaryRedirect(URI.create("/")).build()
								
				)
				.andRoute(RequestPredicates.GET("/index"),
						req -> ServerResponse
								.ok()
								.render("index",
										req.exchange().getAttributes())
				);
	}
}
