package kr.co.wishDream.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.handler.PetHandler;

@Configuration
public class PetRouter {

	@Bean
	RouterFunction<ServerResponse> petRoutes(PetHandler petHandler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/pet/dailyLife/grid")
					.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), request -> {
						return petHandler.dailyLifeGrid(request);
				});
	}
}
