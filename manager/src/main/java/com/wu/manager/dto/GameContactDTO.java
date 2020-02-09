package com.wu.manager.dto;

import lombok.Data;

/**
 * @program: iosbbs
 * @description: 游戏联系方式数据传输类
 * @author: Wu
 * @create: 2020-02-09 10:20
 **/

@Data
public class GameContactDTO {
    private Integer id;

    private GameDTO game;

    private String qq;

    private String phone;

    private String description;
}
