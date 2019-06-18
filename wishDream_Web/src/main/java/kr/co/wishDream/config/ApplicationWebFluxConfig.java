package kr.co.wishDream.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.resource.VersionResourceResolver;
import org.springframework.web.reactive.result.view.HttpMessageWriterView;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * TODO : 설정 하다 말음, 화면 띄우는 작업 해야함
 * db : rxjava2-jdbc & postgresql or mssql
 * @author jyKim
 *
 */

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = {"kr.co.wishDream"})
public class ApplicationWebFluxConfig implements ApplicationContextAware, WebFluxConfigurer {

	@Autowired
	private ObjectMapper objectMapper;
	
	private ApplicationContext context;
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;
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
	
	@Bean
	public MessageSource messageSource() {
	  ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	  messageSource.setBasenames("i18n/messages");
	  messageSource.setDefaultEncoding("UTF-8");
	  return messageSource;
	}
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths("classpath:/resources/static/");
		return configurer;
	}
	
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setApplicationContext(this.context);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl"); 
		return resolver;
	}

	@Override
	public void configureContentTypeResolver(RequestedContentTypeResolverBuilder builder) {
		builder.fixedResolver(MediaType.ALL);
		builder.parameterResolver();
		builder.headerResolver();
	}

	@Override
	public void configurePathMatching(PathMatchConfigurer configurer) {
		configurer
			.setUseCaseSensitiveMatch(true)
			.setUseTrailingSlashMatch(false)
			.addPathPrefix("/", HandlerTypePredicate.forAnnotation(RestController.class));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/**")
			.addResourceLocations("classpath:/resources/", "classpath:/resources/static/","/public/")
			.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
			.resourceChain(true)
			.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}

	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
		configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
		configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.freeMarker();
		Jackson2JsonEncoder encoder = new Jackson2JsonEncoder();
		registry.defaultViews(new HttpMessageWriterView(encoder));
	}

	
	
	
}
