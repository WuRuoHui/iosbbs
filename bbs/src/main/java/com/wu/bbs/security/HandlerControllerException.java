package com.wu.bbs.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class HandlerControllerException {

    @ExceptionHandler(RuntimeException.class)
    public String handlerException(RuntimeException e) {
        if (e instanceof AccessDeniedException) {
            return "forward:/404";
        }
        return "forward:/500";
    }
}
