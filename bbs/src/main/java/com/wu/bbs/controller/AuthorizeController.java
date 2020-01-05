/**
 * @program: kefubbs
 * @description: 登录controller
 * @author: Wu
 * @create: 2019-12-10 13:42
 **/
package com.wu.bbs.controller;


import com.wu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorizeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String showLogin() {
        return "user/login";
    }

/*    @PostMapping("/login")
    @ResponseBody
    public LayUIResult login(String username, String password) throws IOException {
        LayUIResult layUIResult = userService.login(username, password);
        System.out.println(username + ":" + password);
        return layUIResult;
    }*/
}