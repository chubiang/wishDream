package kr.co.wishDream.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.filter.CsrfHeaderFilter;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(proxyTargetClass = true)
public class SecurityWebFluxConfig extends ReactiveAuthenticationManagerAdapter{


	public SecurityWebFluxConfig(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}


	@Autowired
	@Qualifier("customUserDetailService")
	private ReactiveUserDetailsService customUserDetailsService;
	
	@Autowired
	@Qualifier("customAuthenticationSuccessHandler")
	private ServerAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	@Qualifier("customAuthenticationFailureHandler")
	private ServerAuthenticationFailureHandler customAuthenticationFailureHandler;

   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
	
	@Bean
	public ServerSecurityContextRepository securityContextRepository() {
		WebSessionServerSecurityContextRepository securityContextRepository =
				new WebSessionServerSecurityContextRepository();
		
		securityContextRepository.setSpringSecurityContextAttrName("custom-security-context");
		return securityContextRepository;
	}
	
	@Bean
	public ReactiveAuthenticationManager customauthenticationManager() {
		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
				new UserDetailsRepositoryReactiveAuthenticationManager(customUserDetailsService);
		return authenticationManager;
	}
	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return 
			http.authorizeExchange().pathMatchers("/resources/**", "/favicon.ico", "/styles/**", "/login", "/logout", "/static/**")
			.permitAll()
			.and().httpBasic()
			.and().securityContextRepository(securityContextRepository())
			.csrf()
			.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
			.and().addFilterAfter(new CsrfHeaderFilter(), SecurityWebFiltersOrder.CSRF)
			.formLogin()
			.loginPage("/login")
			.authenticationSuccessHandler(customAuthenticationSuccessHandler)
			.authenticationFailureHandler(customAuthenticationFailureHandler)
			.and().exceptionHandling()
			.authenticationEntryPoint((exchange, exception) -> Mono.error(exception))
			.accessDeniedHandler((exchange, exception) -> Mono.error(exception))
			.and().logout()
			.logoutUrl("/logout")
			.and().build();
	}
}
