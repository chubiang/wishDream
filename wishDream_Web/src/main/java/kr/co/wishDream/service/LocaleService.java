package kr.co.wishDream.service;

import org.springframework.web.server.ServerWebExchange;

public interface LocaleService {
	public String getMessage(String code, ServerWebExchange exchange);
}
