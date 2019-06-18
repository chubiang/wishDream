package kr.co.wishDream.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.server.ServerWebExchange;

import kr.co.wishDream.config.resolver.LocaleResolver;
import kr.co.wishDream.service.LocaleService;

public class LocaleServiceImpl implements LocaleService {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public String getMessage(String code, ServerWebExchange exchange) {
		LocaleContext localeContext = localeResolver.resolveLocaleContext(exchange);
		return messageSource.getMessage(code, null, localeContext.getLocale());
	}

}
