package kr.co.wishDream.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.CacheControl;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.resource.VersionResourceResolver;
import org.springframework.web.reactive.result.view.HttpMessageWriterView;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.co.wishDream.config.resolver.LocaleResolver;
import kr.co.wishDream.filter.BaseWebFilter;
import kr.co.wishDream.handler.AlarmConsumerHandler;
import kr.co.wishDream.handler.AlarmProducerHandler;
import kr.co.wishDream.handler.AlarmWebSocketHandler;
import kr.co.wishDream.handler.EventEmitterHandler;

/**
 * db : rxjava2-jdbc = postgresql
 * @author jyKim
 *
 */

@Configuration
@EnableWebFlux
@Import({KafkaProducer.class, KafkaConsumer.class})
@ComponentScan(basePackages = {"kr.co.wishDream"})
public class ApplicationWebFluxConfig implements ApplicationContextAware, WebFluxConfigurer {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	AlarmConsumerHandler alarmConsumerHandler;
	
	@Autowired
	AlarmProducerHandler alarmProducerHandler;
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Bean
	public LocaleContextResolver createLocaleContextResolver() {
		return new LocaleResolver();
	}
	
	@Bean
	@Order(1)
	public BaseWebFilter baseWebFilter() {
		return new BaseWebFilter();
	}
	
	@Bean
	public HandlerMapping handlerMapping() {
		Map<String, WebSocketHandler> map = new HashMap<>();
		map.put("/topic/info", new EventEmitterHandler());
		map.put("/topic/alarm", new AlarmWebSocketHandler());
		map.put("/topic/kafkaAlarm", alarmConsumerHandler);
		map.put("/topic/kafkaProducerAlarm", alarmProducerHandler);
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setUrlMap(map);
		mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return mapping;
	}
	
	@Bean
	public WebSocketHandlerAdapter handlerAdapter() {
		return new WebSocketHandlerAdapter(webSocketService());
	}
	
	@Bean
	public WebSocketService webSocketService() {
		return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
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

	@Override
	public void configurePathMatching(PathMatchConfigurer configurer) {
		configurer
			.setUseCaseSensitiveMatch(true)
			.setUseTrailingSlashMatch(false)
			.addPathPrefix("/index/", HandlerTypePredicate.forAnnotation(RestController.class));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/static/**","/*.ico", "/images/**", "/json/**")
			.addResourceLocations("classpath:/static/", "classpath:/images/", "classpath:/json/")
			.setCacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
			.resourceChain(true)
			.addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
	}
	
	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
		configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
	}
	
	@Bean
	public ITemplateResolver thymeleafTemplateResolver() {
	  final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
	  resolver.setApplicationContext(this.applicationContext);
	  resolver.setPrefix("classpath:static/");
	  resolver.setSuffix(".html");
	  resolver.setTemplateMode(TemplateMode.HTML);
	  resolver.setCacheable(false);
	  resolver.setCheckExistence(false);
	  return resolver;
	}

	@Bean
	public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
	  SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
	  templateEngine.setTemplateResolver(thymeleafTemplateResolver());
	  return templateEngine;
	}

	@Bean
	public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver() {
	  ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
	  viewResolver.setTemplateEngine(thymeleafTemplateEngine());
	  return viewResolver;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		Jackson2JsonEncoder encoder = new Jackson2JsonEncoder();
		registry.defaultViews(new HttpMessageWriterView(encoder));
		registry.viewResolver(thymeleafReactiveViewResolver());
	}
	
}
