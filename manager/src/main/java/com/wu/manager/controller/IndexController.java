package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 首页controller
 * @author: Wu
 * @create: 2020-01-05 23:46
 **/
@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/menu/topMenu")
    @ResponseBody
    public LayUIResult topMenu(){
        LayUIResult layUIResult = menuService.getAllTopMenu();
        return layUIResult;
    }
}
