package com.wu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 帮助文档controller
 * @author: Wu
 * @create: 2020-01-08 08:16
 **/
@Controller
public class DocController {

    @RequestMapping("/page/doc/{pageName}")
    public String showDocPage(@PathVariable(name = "pageName")String pageName) {
        return "page/doc/"+pageName;
    }
}
