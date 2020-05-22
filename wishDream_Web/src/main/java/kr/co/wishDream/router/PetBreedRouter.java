package kr.co.wishDream.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.handler.PetBreedHandler;
import reactor.core.publisher.Mono;

@Configuration
public class PetBreedRouter {

	private Logger LOG = LoggerFactory.getLogger(PetBreedRouter.class);
	
	@Bean
	RouterFunction<ServerResponse> petBreedRoutes(PetBreedHandler handler) throws Exception {
		return RouterFunctions
				.route(RequestPredicates.GET("/json/petBreed")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						request -> { 
							try {
								return handler.getPetBreedCategory();
							} catch (Exception e) {
								e.printStackTrace();
							}
							return ServerResponse
									.ok()
									.contentType(MediaType.APPLICATION_JSON)
									.body(Mono.error(new NullPointerException("No data on PetBreed")), String.class);
						}
				).andRoute(RequestPredicates.GET("/json/petBreed/{breedId}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						request -> { 
							try {
								Integer breedId = new Integer(request.pathVariable("breedId"));
								return handler.getSubPetBreed(breedId);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return ServerResponse
									.ok()
									.contentType(MediaType.APPLICATION_JSON)
									.body(Mono.error(new NullPointerException("No data on PetBreed")), String.class);
						});
	}
	
}
