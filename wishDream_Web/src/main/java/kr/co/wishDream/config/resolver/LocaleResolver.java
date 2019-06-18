package kr.co.wishDream.config.resolver;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

public class LocaleResolver implements LocaleContextResolver{


	@Override
	public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
		String lang = exchange.getRequest().getHeaders().getFirst("Accept-Language");
		
		Locale targetLocale = Locale.getDefault();
		if(lang != null && !lang.isEmpty()) {
			targetLocale = Locale.forLanguageTag(lang);
		}
		return new SimpleLocaleContext(targetLocale);
	}

	@Override
	public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
		throw new UnsupportedOperationException("Not Supported");
	}

}
