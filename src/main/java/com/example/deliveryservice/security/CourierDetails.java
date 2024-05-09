package com.example.deliveryservice.security;

import com.example.deliveryservice.dto.CourierDto;
import com.example.deliveryservice.entities.Courier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CourierDetails implements UserDetails {

    private Courier courier;

    public CourierDetails(Courier courier){
        this.courier = courier;
    }

    public CourierDetails(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_COURIER"));
        return authorities;
    }

    public Integer getId(){
        return courier.getId();
    }

    @Override
    public String getPassword() {
        return courier.getPassword();
    }

    @Override
    public String getUsername() {
        return courier.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
