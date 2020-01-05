package com.wu.bbs.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {
    private Integer id;

    private String name;

    private String description;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }
}