package kr.co.wishDream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.server.i18n.LocaleContextResolver;

import kr.co.wishDream.config.resolver.LocaleResolver;

@Configuration
public class LocaleConfig extends DelegatingWebFluxConfiguration{

	@Override
	protected LocaleContextResolver createLocaleContextResolver() {
		return new LocaleResolver();
	}
	
}
