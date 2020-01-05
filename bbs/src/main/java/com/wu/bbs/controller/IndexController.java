/**
 * @program: kefubbs
 * @description: 主页controller
 * @author: Wu
 * @create: 2019-12-10 13:31
 **/
package com.wu.bbs.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}

