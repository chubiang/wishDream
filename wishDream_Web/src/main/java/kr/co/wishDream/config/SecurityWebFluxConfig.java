package kr.co.wishDream.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesRegistrationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
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
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public InMemoryClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> registrations = new ArrayList<ClientRegistration>(
				OAuth2ClientPropertiesRegistrationAdapter
					.getClientRegistrations(this.properties).values());
		return new InMemoryClientRegistrationRepository(registrations);
	}
	
//	@Bean
//	public WebClient rest(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
//	    ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
//	            new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz);
//	    return WebClient.builder()
//	            .filter(oauth2).build();
//	}
	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return 
			http.authorizeExchange()
				.pathMatchers("/resources/**", "/favicon.ico", "/styles/**", "/login", "/logout", "/static/**", "/topic/**")
				.permitAll()
			.and().httpBasic()
			.and().cors().configurationSource(corsConfigurationSource())
			.and().csrf()
				.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
			.and().addFilterAfter(new CsrfHeaderFilter(), SecurityWebFiltersOrder.CSRF)
			.exceptionHandling().authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/login"))
			.and().authorizeExchange().anyExchange().authenticated()
			.and().oauth2Login()
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
