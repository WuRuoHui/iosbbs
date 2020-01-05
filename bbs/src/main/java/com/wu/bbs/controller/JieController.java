package com.wu.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JieController {

    @RequestMapping("/jie/{pageName}")
    public String showJiePage(@PathVariable(name = "pageName") String pageName) {
        return "jie/"+pageName;
    }
}
