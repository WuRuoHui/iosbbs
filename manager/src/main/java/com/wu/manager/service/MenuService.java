package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.LeftNav;
import com.wu.manager.pojo.LeftNavNode;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

/**
 * @description: 菜单项service
 * @author: Wu
 * @create: 2020-01-06 21:33
 **/

public interface MenuService {

    public LayUIResult getAllTopMenu();

    public Map<String, List<LeftNavNode>> getAllLeftNav(Authentication authentication);

    LayUIResult selectLeftNav();

    LayUIResult selectTopMenu();

    LayUIResult insertOrUpdateLeftNav(LeftNav leftNav);

    LayUIResult deleteLeftNavById(Integer id);

    LayUIResult updateLeftNavStatus(Integer id);
}
