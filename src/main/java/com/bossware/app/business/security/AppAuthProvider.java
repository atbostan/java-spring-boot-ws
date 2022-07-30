package com.bossware.app.business.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.bossware.app.business.services.UserService;
import com.bossware.app.persistance.repositories.AuthorityRepository;
import com.bossware.app.persistance.repositories.RoleRepository;
import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.entities.Authority;
import com.bossware.app.shared.entities.Role;
import com.bossware.app.shared.entities.User;

@Component
public class AppAuthProvider implements AuthenticationProvider  {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthorityRepository authRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String credentialOne = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User user = userRepository.findUserByEmail(credentialOne);
		List<Role> roles = roleRepository.findAllByUser(user);
		if (user!=null) {
			if (encoder.matches(pwd, user.getPassword())) {
				
				return new UsernamePasswordAuthenticationToken(credentialOne, pwd, getAuthsByRole(roles.get(0)));

		
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		}
		else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);	
	}
	
	private List<GrantedAuthority> getAuthsByRole (Role role){
		List<GrantedAuthority> grantedAuth = new ArrayList<>();
		List<Authority> authList = authRepository.findAllByRole(role);
		for (Authority authority : authList) {
			grantedAuth.add(new SimpleGrantedAuthority(authority.getAuthName()));
		}
		return grantedAuth;
			
		
	}
	
}
