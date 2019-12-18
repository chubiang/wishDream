package kr.co.wishDream.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler{

	private Logger LOG = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
		
		LOG.info("CustomAccessDeniedHandler "+denied.getStackTrace());
		exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
		
		return Mono.error(denied.getCause());
	}

}
