package kr.co.wishDream.webClient;

import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class ErrorDetails {

	WebClient webClient = WebClient.builder()
		    .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
		        if (clientResponse.statusCode().isError()) {
		            return clientResponse.bodyToMono(ErrorDetails.class)
		                    .flatMap(errorDetails -> Mono.error(new CustomClientException(clientResponse.statusCode(), errorDetails)));
		        }
		        return Mono.just(clientResponse);
		    }))
		    .build();
	
}
