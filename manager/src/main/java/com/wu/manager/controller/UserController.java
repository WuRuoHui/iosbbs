package com.wu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 用户controller
 * @author: Wu
 * @create: 2020-01-08 08:33
 **/

@Controller
public class UserController {

    @RequestMapping("/page/user/{pageName}")
    public String showUserPage(@PathVariable(name = "pageName")String pageName) {
        return "page/user/"+pageName;
    }
}
