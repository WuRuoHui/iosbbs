package com.wu.manager.service.impl;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.LeftNavMapper;
import com.wu.manager.mapper.TopMenuMapper;
import com.wu.manager.pojo.*;
import com.wu.manager.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 菜单项service实现类
 * @author: Wu
 * @create: 2020-01-06 21:34
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private TopMenuMapper topMenuMapper;
    @Autowired
    private LeftNavMapper leftNavMapper;

    @Override
    public LayUIResult getAllTopMenu() {
        List<TopMenu> topMenus = topMenuMapper.selectByExample(new TopMenuExample());

        return LayUIResult.ok(JsonUtils.objectToJson(topMenus));
    }


    //待重构
    @Override
    public Map<String,List<LeftNavNode>> getAllLeftNav() {
        List<TopMenu> topMenus = topMenuMapper.selectByExample(new TopMenuExample());
        Map<String,List<LeftNavNode>> map= new HashMap<>();
        for (TopMenu topMenu : topMenus) {
            //获得二级菜单
            LeftNavExample leftNavExample = new LeftNavExample();
            leftNavExample.createCriteria().andParentIdEqualTo(topMenu.getId()).andMenuLevelEqualTo(2);
            List<LeftNav> leftSecNavs = leftNavMapper.selectByExample(leftNavExample);
            List<LeftNavNode> leftNavNodes = new ArrayList<>();
            for (LeftNav leftNav : leftSecNavs) {
                LeftNavNode leftNavNode = new LeftNavNode();
                BeanUtils.copyProperties(leftNav,leftNavNode);
                //如果二级菜单是父节点，即有子节点
                if (leftNav.getIsParent()) {
//                    LeftNavExample leftThrNavExample = new LeftNavExample();
                    leftNavExample.createCriteria().andMenuLevelEqualTo(3).andParentIdEqualTo(leftNav.getId());
                    List<LeftNav> leftThrNavs = leftNavMapper.selectByExample(leftNavExample);
                    List<LeftNavNode> navThrNodes = new ArrayList<>();      //三级菜单list容器
                    for (LeftNav leftNav1 : leftThrNavs) {
                        LeftNavNode leftThrNavNode = new LeftNavNode();
                        BeanUtils.copyProperties(leftNav1,leftThrNavNode);
                        navThrNodes.add(leftThrNavNode);
                    }
                    leftNavNode.setChildren(navThrNodes);
                }
                leftNavNodes.add(leftNavNode);
            }
            map.put(topMenu.getParameterName(),leftNavNodes);
        }
        return map;
    }
}
