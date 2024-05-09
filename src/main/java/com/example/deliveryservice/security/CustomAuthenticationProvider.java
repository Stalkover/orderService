package com.example.deliveryservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private CourierDetailsService courierDetailsService;
    @Autowired
    private @Lazy PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String encodedPassword = passwordEncoder.encode(password);
        UserDetails userDetails = clientDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            userDetails = courierDetailsService.loadUserByUsername(username);
            if(userDetails == null){
                throw new UsernameNotFoundException("User not found");
            }
        }
        System.out.println(userDetails.getAuthorities());

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new AuthenticationException("Invalid credentials") {};
        }

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                userDetails, password, authorities);
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
