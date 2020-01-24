package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.LeftNavNode;

import java.util.List;
import java.util.Map;

/**
 * @description: 菜单项service
 * @author: Wu
 * @create: 2020-01-06 21:33
 **/

public interface MenuService {

    public LayUIResult getAllTopMenu();

    public Map<String, List<LeftNavNode>> getAllLeftNav();

    LayUIResult selectLeftNav();
}
