package kr.co.wishDream.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class CustomAuthenticationFailureHandler implements ServerAuthenticationFailureHandler{
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	
	@Override
	public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
		ServerWebExchange exchange = webFilterExchange.getExchange();
		
		LOG.info("onAuthenticationFailure "+exception.getMessage());
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		return exchange.getResponse().setComplete().doOnSuccess(error -> LOG.info(exception.getMessage()));
	}

}
