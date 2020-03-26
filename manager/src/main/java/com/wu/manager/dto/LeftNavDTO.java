package com.wu.manager.dto;

import com.wu.manager.pojo.TopMenu;
import lombok.Data;

/**
 * @program: iosbbs
 * @description: 左侧菜单数据传输类
 * @author: Wu
 * @create: 2020-01-25 00:15
 **/

@Data
public class LeftNavDTO {

    private Integer id;

    private String title;

    private String icon;

    private String href;

    private Boolean spread;

    private Integer menuLevel;

    private Boolean isParent;

    private TopMenu parent;

    private Boolean status;
}
