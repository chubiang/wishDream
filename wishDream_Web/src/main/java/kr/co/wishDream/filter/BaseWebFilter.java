package kr.co.wishDream.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import reactor.core.publisher.Mono;

public class BaseWebFilter implements WebFilter{

	private final PathPattern pathPattern;
	
	public BaseWebFilter() {
		pathPattern = new PathPatternParser().parse("/");
	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		final ServerHttpRequest request = exchange.getRequest();
		
		if (pathPattern.matches(request.getPath().pathWithinApplication())) {
			if (exchange.getAttributes().get("_csrf_token").toString().isEmpty()) {
				return chain.filter(exchange
										.mutate().request(
												exchange
													.getRequest()
													.mutate()
													.path("/login")
													.build()
										).build());
			}
			return chain.filter(exchange
									.mutate().request(
											exchange
												.getRequest()
												.mutate()
												.path("/index")
												.build()
									).build()
								);
		}
		return chain.filter(exchange);
	}

}
