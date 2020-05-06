package kr.co.wishDream.webClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class RequestWebClient {

	@Autowired
	WebClient webClient;
	
	public Mono<?> goAuthroizePage() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/oauth2/registration")
				.queryParam("id")
				.build())
				.retrieve();
		return null;
	}
}
