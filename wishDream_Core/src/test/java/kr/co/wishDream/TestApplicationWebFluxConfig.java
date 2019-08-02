package kr.co.wishDream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.HttpMessageWriterView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author jyKim
 *
 */

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = {"kr.co.wishDream"})
public class TestApplicationWebFluxConfig implements ApplicationContextAware, WebFluxConfigurer {

	@Autowired
	private ObjectMapper objectMapper;
	
	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder.json()
				.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.modules(new JavaTimeModule())
				.build();
	}
	
	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
		configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		Jackson2JsonEncoder encoder = new Jackson2JsonEncoder();
		registry.defaultViews(new HttpMessageWriterView(encoder));
	}
	
}
