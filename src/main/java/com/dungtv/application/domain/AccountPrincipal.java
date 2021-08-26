package com.dungtv.application.domain;

import java.util.Iterator;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountPrincipal implements UserDetails
{
    private Account account;
    
    public AccountPrincipal(final Account account) {
        this.account = account;
    }
    
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(final Account account) {
        this.account = account;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final Role role : this.account.getRoles()) {
            authorities.add((GrantedAuthority)new SimpleGrantedAuthority(role.getCode()));
        }
        return authorities;
    }
    
    public String getPassword() {
        return this.account.getPassword();
    }
    
    public String getUsername() {
        return this.account.getUsername();
    }
    
    public boolean isAccountNonExpired() {
        return true;
    }
    
    public boolean isAccountNonLocked() {
        return true;
    }
    
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    public boolean isEnabled() {
        return true;
    }
}