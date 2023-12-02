package com.warehousemanagement.wms.security;

import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Operator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NewUserDetails implements UserDetails {

    private final String userName;
    private final String password;
    private final String avatar;
    private final Integer id;
    private List<GrantedAuthority> authorities;

    public NewUserDetails(Administrator administrator) {
        this.userName=administrator.getNickname();
        this.password=administrator.getPassword();
        this.avatar=administrator.getAvatar();
        this.id=administrator.getId();
        this.authorities = new ArrayList<>();
        if(administrator.getId()==1)
            this.authorities.add(new SimpleGrantedAuthority("ROLE_MAIN"));
        else
            this.authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    public NewUserDetails(Operator operator) {
        this.userName=operator.getNickname();
        this.password=operator.getPassword();
        this.avatar=operator.getAvatar();
        this.id=operator.getId();
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority("ROLE_OPERATOR"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void addRole(String role) {
        this.authorities.add(new SimpleGrantedAuthority(role));
    }

    public Integer getId() {
        return id;
    }

    public String getAvatar(){
        return avatar;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
