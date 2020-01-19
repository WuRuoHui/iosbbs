package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.UserDTO;
import com.wu.manager.pojo.User;
import com.wu.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/user/userGrade",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult showUserGrade() {
        LayUIResult layUIResult = userService.selectAllUserGrade();
        return layUIResult;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectUser() {
        LayUIResult layUIResult = userService.selectAllUser();
        return layUIResult;
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public LayUIResult insertUser(User user,Integer role) {
        LayUIResult layUIResult = userService.insertUser(user,role);
        return layUIResult;
    }
}