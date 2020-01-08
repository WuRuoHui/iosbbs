package com.wu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 文章controller
 * @author: Wu
 * @create: 2020-01-08 08:19
 **/

@Controller
public class NewsController {

    @RequestMapping("/page/news/{pageName}")
    public String showNewsPage(@PathVariable(name = "pageName") String pageName) {
        return "page/news/"+pageName;
    }
}
