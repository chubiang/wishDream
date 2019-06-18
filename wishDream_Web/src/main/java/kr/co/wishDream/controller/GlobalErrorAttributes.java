package kr.co.wishDream.controller;

import java.util.Map;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;


public class GlobalErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		// TODO Auto-generated method stub
		Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
		map.put("status", HttpStatus.BAD_REQUEST);
		map.put("message", "Bad request!");
		return map;
	}

	@Override
	public Throwable getError(ServerRequest request) {
		// TODO Auto-generated method stub
		return super.getError(request);
	}

	@Override
	public void storeErrorInformation(Throwable error, ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		super.storeErrorInformation(error, exchange);
	}


}
