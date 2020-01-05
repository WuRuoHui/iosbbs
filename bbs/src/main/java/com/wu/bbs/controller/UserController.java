package com.wu.bbs.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
//@Secured({"ROLE_MANAGER","ROLE_USER"})
//@RolesAllowed({"ROLE_USER","ROLE_MANAGER"})
public class UserController {

    @RequestMapping("/user/{pageName}")
    public String showUserPage(@PathVariable(name = "pageName") String pageName) {
        return "user/"+pageName;
    }
}
