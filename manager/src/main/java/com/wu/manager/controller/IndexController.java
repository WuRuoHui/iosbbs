package com.wu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 首页controller
 * @author: Wu
 * @create: 2020-01-05 23:46
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/page/main")
    public String showMain() {
        return "page/main";
    }

    @RequestMapping("/page/404")
    public String showErrorPage() {
        return "page/404";
    }

    @RequestMapping("/page/login/login")
    public String showLoginPage() {
        return "page/login/login";
    }
}
