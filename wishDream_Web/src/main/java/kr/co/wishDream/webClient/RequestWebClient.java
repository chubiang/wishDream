package kr.co.wishDream.webClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class RequestWebClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestWebClient.class.getName());
	private final WebClient webClient;
	
	public RequestWebClient() {
		this.webClient = WebClient.builder().baseUrl("http://localhost:8080/")
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.build();
	}
	
}
