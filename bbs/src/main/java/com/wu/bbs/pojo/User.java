package com.wu.bbs.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@Data
public class User implements UserDetails {
    private Integer id;

    private String username;

    private String password;

    private Long gmtCreate;

    private Long gmtModified;

    private String avatarUrl;

    private Integer vipLevel;

    private String vipName;

    private Boolean status;

    private String name;

    private Integer sex;

    private String description;

    private List<Role> roleList = new ArrayList();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList;
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