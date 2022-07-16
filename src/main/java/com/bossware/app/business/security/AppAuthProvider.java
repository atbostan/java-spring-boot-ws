package com.bossware.app.business.security;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.entities.User;

@Component
public class AppAuthProvider implements AuthenticationProvider  {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String credentialOne = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User user = userRepository.findUserByEmail(credentialOne);
		if (user!=null) {
			if (encoder.matches(pwd, user.getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("Admin"));
				return new UsernamePasswordAuthenticationToken(credentialOne, pwd, authorities);
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);	
	}

}
