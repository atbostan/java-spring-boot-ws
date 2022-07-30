package com.bossware.app.core.configurations;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class AppSecurityConfig {


	@Value("${client.accepted.url}")
	private String clientUrl;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private static final String[] AUTH_WHITELIST = {
	        "/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**",
	        "/configuration/**",
	        "/swagger*/**"
	};
	
	private static final String[] AUTH_BLACKLIST = {
	};


	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.cors().configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList(clientUrl));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;	
			}
		})
		.and()
		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
		.authorizeHttpRequests((auth) -> auth.antMatchers("/users").hasAuthority("CRUD")
				.antMatchers(AUTH_WHITELIST).permitAll()).
		httpBasic(Customizer.withDefaults());

		return http.build();

    

	}
	
	/**
	  @Bean
	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

	        http.cors().configurationSource(new CorsConfigurationSource() {
	                    @Override
	                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                        CorsConfiguration config = new CorsConfiguration();
	                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	                        config.setAllowedMethods(Collections.singletonList("*"));
	                        config.setAllowCredentials(true);
	                        config.setAllowedHeaders(Collections.singletonList("*"));
	                        config.setMaxAge(3600L);
	                        return config;
	                    }
	                }).and().csrf().ignoringAntMatchers("/contact").
	                csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	                .and().authorizeHttpRequests((auth) -> auth
	                        .antMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
	                        .antMatchers("/notices", "/contact").permitAll()
	                ).httpBasic(Customizer.withDefaults());
	        return http.build();

	    }
	    
	    */

    	
}
