package com.wu.bbs.DTO;

import com.wu.bbs.pojo.UserGrade;
import lombok.Data;

/**
 * @program: iosbbs
 * @description: 属性少的用户数据传输类
 * @author: Wu
 * @create: 2020-02-13 10:36
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
