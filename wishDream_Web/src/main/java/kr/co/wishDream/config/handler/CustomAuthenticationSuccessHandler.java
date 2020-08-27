package kr.co.wishDream.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class CustomAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler{

	private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	
	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
		ServerWebExchange exchange = webFilterExchange.getExchange();
		return webFilterExchange.getChain().filter(exchange).doOnSuccess(ok -> {
			LOG.info("[Security] Authentication Success ==== " + authentication);
		});
	}

}
