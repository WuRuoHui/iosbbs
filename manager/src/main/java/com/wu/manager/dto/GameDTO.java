package com.wu.manager.dto;

import com.wu.manager.pojo.Dept;
import com.wu.manager.pojo.Game;
import lombok.Data;

/**
 * @program: iosbbs
 * @description: 游戏信息数据传输类
 * @author: Wu
 * @create: 2020-02-01 12:40
 **/

@Data
public class GameDTO {

    private Integer id;

    private String name;

    private Boolean status;

    private Long gmtCreate;

    private Dept dept;

    private Integer sortOrder;

    private Game parent;

    private Boolean isParent;

    private Integer edition;

}
