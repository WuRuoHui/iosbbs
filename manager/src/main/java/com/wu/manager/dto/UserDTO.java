package com.wu.manager.dto;

import com.wu.manager.pojo.UserGrade;
import lombok.Data;

/**
 * @program: iosbbs
 * @description: 用户传输类
 * @author: Wu
 * @create: 2020-01-19 20:21
 **/

@Data
public class UserDTO {
    private Integer id;

    private String username;

    private String password;

    private Long gmtCreate;

    private Long gmtModified;

    private String avatarUrl;

    private UserGrade userGrade;

    private Boolean status;

    private String name;

    private Integer sex;

    private String description;

}
