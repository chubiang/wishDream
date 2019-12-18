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
		exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
		
		LOG.info("onAuthenticationFailure");
		// 비밀번호 틀리면 로그인 한적있어도 튕겨내게 해야함 -> url - login?failed 변경 
		return webFilterExchange.getChain().filter(exchange)
				.onErrorResume(AuthenticationException.class, err -> Mono.error(err));
	}

}
