package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.service.UserService;
import com.wu.manager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 用户controller
 * @author: Wu
 * @create: 2020-01-08 08:33
 **/

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/page/user/{pageName}")
    public String showUserPage(@PathVariable(name = "pageName")String pageName) {
        return "page/user/"+pageName;
    }

    @RequestMapping("/user/userGrade")
    @ResponseBody
    public LayUIResult showUserGrade() {
        LayUIResult layUIResult = userService.selectAllUserGrade();
        return layUIResult;
    }

}
