package kr.co.wishDream.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesRegistrationAdapter;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.oauth2.client.web.server.ServerAuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.server.WebSessionOAuth2ServerAuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

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
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
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
	public ReactiveOAuth2AuthorizedClientService authorizedClientService() {
		return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository());
	}
	
	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return 
			http.authorizeExchange()
				.pathMatchers("/images/**", "/login/oauth2/**", "/oauth2/**", "/favicon.ico", "/styles/**", "/login", "/logout", "/static/**", "/topic/**")
				.permitAll()
			.and().oauth2Login()
			.clientRegistrationRepository(clientRegistrationRepository())
			.authorizedClientService(authorizedClientService())
			.and().httpBasic()
			.and().cors().configurationSource(corsConfigurationSource())
			.and().csrf()
				.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
			.and().addFilterAfter(new CsrfHeaderFilter(), SecurityWebFiltersOrder.CSRF)
			.exceptionHandling().authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/login"))
			.and().authorizeExchange().anyExchange().authenticated()
			.and().formLogin()
				.loginPage("/login")
				.authenticationSuccessHandler(customAuthenticationSuccessHandler)
				.authenticationFailureHandler(customAuthenticationFailureHandler)
			.and().logout().logoutUrl("/logout")
			.and().authenticationManager(customauthenticationManager())
				.exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler)
			.and().build();
	}
}
