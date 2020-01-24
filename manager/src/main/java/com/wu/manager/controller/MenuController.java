package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.LeftNavMapper;
import com.wu.manager.pojo.LeftNavNode;
import com.wu.manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: iosbbs
 * @description: 菜单controller
 * @author: Wu
 * @create: 2020-01-24 23:29
 **/

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private LeftNavMapper leftNavMapper;

    @RequestMapping("/page/menuManager")
    public String showMenuManager(){
        return "page/menu/menuList";
    }

    @RequestMapping("/menu/topMenu")
    @ResponseBody
    public LayUIResult showTopMenu(){
        LayUIResult layUIResult = menuService.getAllTopMenu();
        return layUIResult;
    }

    @RequestMapping("/menu/leftNav")      //js在index.js中
    @ResponseBody
    public Map<String, List<LeftNavNode>> showLeftNav() {
        Map<String,List<LeftNavNode>> leftNav = menuService.getAllLeftNav();
        return leftNav;
    }

    @RequestMapping("/menu/leftNavWithoutKey")
    @ResponseBody
    public LayUIResult selectLeftNav() {
        LayUIResult layUIResult = menuService.selectLeftNav();
        return layUIResult;
    }
}
