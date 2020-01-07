package com.wu.manager.service.impl;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.TopMenuMapper;
import com.wu.manager.pojo.TopMenu;
import com.wu.manager.pojo.TopMenuExample;
import com.wu.manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 菜单项service实现类
 * @author: Wu
 * @create: 2020-01-06 21:34
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private TopMenuMapper topMenuMapper;

    @Override
    public LayUIResult getAllTopMenu() {
        List<TopMenu> topMenus = topMenuMapper.selectByExample(new TopMenuExample());

        return LayUIResult.ok(JsonUtils.objectToJson(topMenus));
    }
}
