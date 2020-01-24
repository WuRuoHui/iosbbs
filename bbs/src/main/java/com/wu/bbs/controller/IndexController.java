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

@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

}

