package com.wu.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: iosbbs
 * @description: 错误页面跳转
 * @author: Wu
 * @create: 2020-02-13 23:47
 **/

@Controller
public class MyExceptionAdvice implements ErrorController {

    @Autowired
    HttpServletRequest request;

    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            case 404:
                return "other/404";
            case 400:
                return "other/404";
            default:
                return "other/notice";
        }
    }
}
