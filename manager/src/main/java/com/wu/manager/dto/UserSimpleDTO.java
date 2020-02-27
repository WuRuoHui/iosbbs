package com.wu.manager.dto;

import com.wu.manager.pojo.UserGrade;
import lombok.Data;

/**
 * @program: iosbbs
 * @description: 用户数据传输类（属性少）
 * @author: Wu
 * @create: 2020-02-27 16:11
 **/

@Data
public class UserSimpleDTO {

    private Integer id;

    private String username;

//    private String password;

//    private Long gmtCreate;

    private String avatarUrl;

    private UserGrade userGrade;

//    private Boolean status;

    private String name;

//    private Integer sex;

//    private String description;
}
