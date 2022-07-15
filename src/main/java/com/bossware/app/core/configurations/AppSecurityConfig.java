package com.bossware.app.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		/**
		 * Custom configurations as per our requirement
		 */
        http.httpBasic();
		http.csrf().disable();
		http.authorizeHttpRequests((auth) -> auth.antMatchers("/users")
				.permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
		return http.build();

	}
}
