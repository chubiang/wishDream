package kr.co.wishDream.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpCookie;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class CsrfHeaderFilter implements WebFilter {

	private Logger log = LoggerFactory.getLogger(CsrfHeaderFilter.class);

	final static String CSRF_TOKEN_HEADER = "XSRF-TOKEN";
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		log.info("PATH === "+exchange.getRequest().getPath());
		
		HttpCookie cookie = exchange.getRequest().getCookies().get(CSRF_TOKEN_HEADER).iterator().next();
		exchange.getAttributes().putIfAbsent("_csrf_headerName", CSRF_TOKEN_HEADER);
		exchange.getAttributes().putIfAbsent("_csrf_token", cookie.getValue());
		log.info("CSRF HEADER FILTER = " + CSRF_TOKEN_HEADER + " : "+cookie.getValue());
		return chain.filter(exchange);
	}

	
}
