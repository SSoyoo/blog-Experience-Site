package com.ssoyoo.blogExperienceSite.security;

import com.ssoyoo.blogExperienceSite.entity.AdminEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminPrincipal implements UserDetails {

    private AdminEntity adminEntity;

    public AdminPrincipal(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }

    public String getEmail() {
        return adminEntity.getAdminEmail();
    }

    @Override
    public String getPassword() {
        return adminEntity.getAdminPassword();
    }

    @Override
    public String getUsername() {
        return adminEntity.getAdminName();
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
