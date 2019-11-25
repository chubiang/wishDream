package kr.co.wishDream.filter;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class CustomWebFilter implements WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		if (exchange.getRequest().getURI().getPath().equals("/")) {
			return chain.filter(exchange
									.mutate().request(
											exchange
												.getRequest()
												.mutate()
												.path("/")
												.build()
									).build()
								);
		}
		return chain.filter(exchange);
	}

}
