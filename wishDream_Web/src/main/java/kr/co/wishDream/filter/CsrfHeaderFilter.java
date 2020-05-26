package kr.co.wishDream.filter;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class CsrfHeaderFilter implements WebFilter {

	private Logger LOG = LoggerFactory.getLogger(CsrfHeaderFilter.class);

//	final static String CSRF_TOKEN_COOKIE = "XSRF-TOKEN";
//	final static String CSRF_TOKEN_HEADER = "X-XSRF-TOKEN";
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String currentUrl = exchange.getRequest().getPath().value();
		
//		for (Map.Entry<String, List<String>> entry : exchange.getRequest().getHeaders().entrySet()) {
//			LOG.info("REQUEST HEADER = "+entry);
//		}
		
//		Pattern urlMatcher = Pattern.compile("(login)|(\\/)*[!@#$%^&*(),.?\\\\\"~`:{}|<>+=_-]*");
//		MultiValueMap<String, HttpCookie> csrfCookie = exchange.getRequest().getCookies();
//		if (currentUrl != null && !urlMatcher.matcher(currentUrl).find()
//				&& !csrfCookie.isEmpty() && csrfCookie.get(CSRF_TOKEN_COOKIE) != null) {
//			exchange.getAttributes().putIfAbsent("_csrf_headerName", CSRF_TOKEN_COOKIE);
//			exchange.getAttributes().putIfAbsent("_csrf_token", csrfCookie.get(CSRF_TOKEN_COOKIE).iterator().next().getValue());
//			LOG.info("CSRF HEADER FILTER = " + CSRF_TOKEN_COOKIE + " : "+csrfCookie.get(CSRF_TOKEN_COOKIE).iterator().next().getValue());
//		}
		return chain.filter(exchange);
	}

	
}
