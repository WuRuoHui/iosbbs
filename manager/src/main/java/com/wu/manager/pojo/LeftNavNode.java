package com.wu.manager.pojo;

import com.alibaba.druid.sql.visitor.functions.Left;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 左侧菜单栏节点对象
 * @author: Wu
 * @create: 2020-01-07 11:17
 **/
@Data
@ToString
public class LeftNavNode {

    private String title;

    private String icon;

    private String href;

    private Boolean spread;

    private List<LeftNavNode> children;
}
