package kr.co.wishDream.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

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
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return 
			http.authorizeExchange()
				.pathMatchers("/resources/**", "/favicon.ico", "/styles/**", "/login", "/logout", "/static/**")
				.permitAll()
			.and().httpBasic()
			.and().csrf()
				.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
			.and().addFilterAfter(new CsrfHeaderFilter(), SecurityWebFiltersOrder.CSRF)
			.authorizeExchange().anyExchange().authenticated()
			.and().formLogin()
				.loginPage("/login")
				.authenticationSuccessHandler(customAuthenticationSuccessHandler)
				.authenticationFailureHandler(customAuthenticationFailureHandler)
			.and().logout().logoutUrl("logout")
			.and().authenticationManager(customauthenticationManager())
				.exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler)
			.and().build();
	}
}
