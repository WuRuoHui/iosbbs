package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.LeftNav;
import com.wu.manager.pojo.LeftNavNode;
import com.wu.manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/page/menuManager")
    public String showMenuManager(){
        return "page/menu/menuList";
    }

    @RequestMapping("/page/menuAdd")
    public String showMenuAdd() {
        return "page/menu/menuAdd";
    }

    @RequestMapping("/menu/topMenu")
    @ResponseBody
    public LayUIResult showTopMenu(){
        LayUIResult layUIResult = menuService.getAllTopMenu();
        return layUIResult;
    }

    @RequestMapping("/menu/topMenuObject")
    @ResponseBody
    public LayUIResult selectTopMenuObject() {
        LayUIResult layUIResult = menuService.selectTopMenu();
        return layUIResult;
    }

    @RequestMapping("/menu/leftNav")      //js在index.js中
    @ResponseBody
    public Map<String, List<LeftNavNode>> showLeftNav(Authentication authentication) {
        Map<String,List<LeftNavNode>> leftNav = menuService.getAllLeftNav(authentication);
        return leftNav;
    }

    @RequestMapping(value = "/menu/leftNav/status/{id}",method = RequestMethod.PUT)      //js在index.js中
    @ResponseBody
    public LayUIResult updateLeftNavStatus(@PathVariable(name = "id") Integer id){
        LayUIResult layUIResult = menuService.updateLeftNavStatus(id);
        return layUIResult;
    }

    @RequestMapping("/menu/leftNavWithoutKey")
    @ResponseBody
    public LayUIResult selectLeftNav(String search, Integer page, Integer limit) {
        LayUIResult layUIResult = menuService.selectLeftNav(search,page,limit);
        return layUIResult;
    }

    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult insertLeftNav(LeftNav leftNav) {
        LayUIResult layUIResult = menuService.insertOrUpdateLeftNav(leftNav);
        return layUIResult;
    }

    @RequestMapping(value = "/menu/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteMenuById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = menuService.deleteLeftNavById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/menus",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteMenuByIds(@RequestBody List<Integer> menuIds) {
        LayUIResult layUIResult = menuService.deleteMenuByIds(menuIds);
        return layUIResult;
    }

}
