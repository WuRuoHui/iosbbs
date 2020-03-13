package com.wu.manager.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {
    private Integer id;

    private String name;

    private String description;

    @Override
    public String getAuthority() {
        return name;
    }
}