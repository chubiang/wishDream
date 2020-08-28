package kr.co.wishDream.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;

import kr.co.wishDream.filter.CsrfHeaderFilter;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(proxyTargetClass = true)
public class SecurityWebFluxConfig {

	@Autowired
	@Qualifier("customUserDetailService")
	private ReactiveUserDetailsService customUserDetailsService;
	
	@Autowired
	@Qualifier("customAuthenticationSuccessHandler")
	ServerAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	@Qualifier("customAuthenticationFailureHandler")
	private ServerAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	@Qualifier("customAccessDeniedHandler")
	ServerAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	OAuth2ClientProperties properties;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
	
	@Bean
	public ReactiveAuthenticationManager customauthenticationManager() {
		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
				new UserDetailsRepositoryReactiveAuthenticationManager(customUserDetailsService);
		
		authenticationManager.setPasswordEncoder(passwordEncoder());
		return authenticationManager;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		config.setAllowedMethods(Arrays.asList("GET", "POST"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedOrigins(Arrays.asList("http://localhost:8080/**", "ws://localhost:8080/topic/**",
				"https://kapi.kakao.com/**", "https://kauth.kakao.com/oauth/**", 
				"http://localhost:8030/**", "ws://localhost:8030/**"));
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public ReactiveClientRegistrationRepository clientRegistrationRepository() {
		Map<String, Registration> kakao = (Map<String, Registration>) this.properties.getRegistration();
		List<ClientRegistration> registrations = new ArrayList<ClientRegistration>();
		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
				.clientId(kakao.get("kakao").getClientId())
				.clientSecret(kakao.get("kakao").getClientSecret())
				.build());
		return new InMemoryReactiveClientRegistrationRepository(registrations);
	}
	
	@Bean
	public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations, 
			ServerOAuth2AuthorizedClientRepository authorizedClients) {
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = 
				new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, authorizedClients);
		return WebClient.builder()
				.filter(oauth)
				.build();
	}
	
	@Bean
	public ReactiveOAuth2AuthorizedClientService authorizedClientService() {
		return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository());
	}
	
	@Bean
	public ServerOAuth2AuthorizationRequestResolver oAuth2AuthorizationRequestResolver(ReactiveClientRegistrationRepository clientRegistrations) {
		return new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrations);
	}
	
//	@Bean
//	public ServerLogoutSuccessHandler logoutSuccessHandler(String url) {
//		RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
//		successHandler.setLogoutSuccessUrl(URI.create(url));
//		return successHandler;
//	}
	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return 
			http.authorizeExchange()
				.pathMatchers("/images/**", "/login/oauth2/**", "/oauth/**", "/favicon.ico", "/styles/**", 
						"/login", "/signUp", "/join","/logout", "/static/**", "/topic/**", "/json/**")
				.permitAll()
			.and().oauth2Client()
			.and().oauth2Login()
			.clientRegistrationRepository(clientRegistrationRepository())
			.authorizedClientService(authorizedClientService())
			.authorizationRequestResolver(oAuth2AuthorizationRequestResolver(clientRegistrationRepository()))
			.authenticationSuccessHandler(customAuthenticationSuccessHandler)
			.authenticationFailureHandler(customAuthenticationFailureHandler)
			.and().cors().configurationSource(corsConfigurationSource())
			.and().csrf()
				.requireCsrfProtectionMatcher(new PathPatternParserServerWebExchangeMatcher("^(\\/oauth2\\/)|(\\/oauth\\/)", HttpMethod.POST))
				.csrfTokenRepository(new WebSessionServerCsrfTokenRepository())
			.and().addFilterAfter(new CsrfHeaderFilter(), SecurityWebFiltersOrder.CSRF)
			.exceptionHandling().authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/login"))
			.and().httpBasic()
			.and().authorizeExchange().anyExchange().authenticated()
			.and().formLogin()
				.loginPage("/login")
				.authenticationSuccessHandler(customAuthenticationSuccessHandler)
				.authenticationFailureHandler(customAuthenticationFailureHandler)
			.and().logout().logoutUrl("/logout")
//			.logoutSuccessHandler(logoutSuccessHandler("/login"))
			.and().authenticationManager(customauthenticationManager())
				.exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler)
			.and().build();
	}
}
