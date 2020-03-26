package com.wu.manager.service.impl;

import com.wu.common.enums.CustomizeErrorCode;
import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.LeftNavDTO;
import com.wu.manager.mapper.LeftNavExtMapper;
import com.wu.manager.mapper.LeftNavMapper;
import com.wu.manager.mapper.TopMenuMapper;
import com.wu.manager.mapper.UserRoleMapper;
import com.wu.manager.pojo.*;
import com.wu.manager.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private LeftNavExtMapper leftNavExtMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public LayUIResult getAllTopMenu() {
        List<TopMenu> topMenus = topMenuMapper.selectByExample(new TopMenuExample());
        return LayUIResult.ok(JsonUtils.objectToJson(topMenus));
    }


    //待重构
    @Override
    public Map<String,List<LeftNavNode>> getAllLeftNav(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        List<TopMenu> topMenus = topMenuMapper.selectByExample(new TopMenuExample());
        Map<String,List<LeftNavNode>> map= new HashMap<>();
        for (TopMenu topMenu : topMenus) {
            //获得二级菜单
            List<LeftNav> leftSecNavs = leftNavExtMapper.selectLeftMenuByRoleId(userRoles.get(0).getRoleId(), topMenu.getId(), 2,true);
            List<LeftNavNode> leftNavNodes = new ArrayList<>();
            for (LeftNav leftNav : leftSecNavs) {
                LeftNavNode leftNavNode = new LeftNavNode();
                BeanUtils.copyProperties(leftNav,leftNavNode);
                //如果二级菜单是父节点，即有子节点
                if (leftNav.getIsParent()) {
                    LeftNavExample leftThrNavExample = new LeftNavExample();
                    leftThrNavExample.createCriteria().andMenuLevelEqualTo(3).andParentIdEqualTo(leftNav.getId());
                    List<LeftNav> leftThrNavs = leftNavMapper.selectByExample(leftThrNavExample);
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

    @Override
    public LayUIResult selectLeftNav() {
        LeftNavExample leftNavExample = new LeftNavExample();
        List<LeftNav> leftNavs = leftNavMapper.selectByExample(leftNavExample);
        List<LeftNavDTO> leftNavDTOS = new ArrayList<>();
        if (leftNavs!= null && leftNavs.size()>0) {
            for (LeftNav leftNav : leftNavs) {
                LeftNavDTO leftNavDTO = new LeftNavDTO();
                BeanUtils.copyProperties(leftNav,leftNavDTO);
                TopMenu topMenu = topMenuMapper.selectByPrimaryKey(leftNav.getParentId());
                leftNavDTO.setParent(topMenu);
                leftNavDTOS.add(leftNavDTO);
            }
            return LayUIResult.build(0,leftNavs.size(),"success",leftNavDTOS);
        }
        return LayUIResult.build(1,"fail");
    }

    @Override
    public LayUIResult selectTopMenu() {
        TopMenuExample topMenuExample = new TopMenuExample();
        List<TopMenu> topMenus = topMenuMapper.selectByExample(topMenuExample);
        if (topMenus != null && topMenus.size()>0) {
            return LayUIResult.build(0,topMenus.size(),"success",topMenus);
        }
        return LayUIResult.build(1,"fail");
    }

    @Override
    public LayUIResult insertOrUpdateLeftNav(LeftNav leftNav) {
        if (leftNav == null) {
            return LayUIResult.fail("数据为空，操作失败");
        }
        //判断id是否存在
        if (leftNav.getId() != null) {
            //存在则进行更新操作
            int rows = leftNavMapper.updateByPrimaryKeySelective(leftNav);
            if (rows >0) {
                return LayUIResult.build(0,"更新成功");
            }
            return LayUIResult.fail("更新失败");
        }
        //不存在则进行插入操作
        leftNav.setSpread(false);
        int rows = leftNavMapper.insertSelective(leftNav);
        if (rows >0 ) {
            return LayUIResult.build(0,"插入成功");
        }
        return LayUIResult.fail("插入失败");
    }

    @Override
    public LayUIResult deleteLeftNavById(Integer id) {
        LeftNav leftNav = leftNavMapper.selectByPrimaryKey(id);
        if (leftNav == null) {
            return LayUIResult.fail("要删除的菜单项不存在");
        }
        int rows = leftNavMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            return LayUIResult.build(0,"删除菜单项成功");
        }
        return LayUIResult.fail("删除菜单项失败");
    }

    @Override
    public LayUIResult updateLeftNavStatus(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        LeftNav leftNav = leftNavMapper.selectByPrimaryKey(id);
        if (leftNav == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        LeftNav leftNavNew = new LeftNav();
        leftNavNew.setId(id);
        leftNavNew.setStatus(!leftNav.getStatus());
        int rows = leftNavMapper.updateByPrimaryKeySelective(leftNavNew);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }
}
