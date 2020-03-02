package com.wu.bbs.controller;

import com.wu.bbs.DTO.UserDTO;
import com.wu.bbs.pojo.Jie;
import com.wu.bbs.service.JieService;
import com.wu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
//@Secured({"ROLE_MANAGER","ROLE_USER"})
//@RolesAllowed({"ROLE_USER","ROLE_MANAGER"})
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JieService jieService;

    @RequestMapping(value = "/user/index",method = RequestMethod.GET)
    public String showUserPage() {
        return "user/index";
    }

    @RequestMapping(value = "/user/message",method = RequestMethod.GET)
    public String showMessagePage() {
        return "user/message";
    }

    @RequestMapping(value = "/user/set",method = RequestMethod.GET)
    public String showSetPage() {
        return "user/set";
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String showUserInfo(@PathVariable(name = "id") Integer id, Model model) {
        UserDTO user = userService.selectUserById(id);
        List<Jie> jies = jieService.selectQuizJieByCreator(id);
        model.addAttribute("user",user);
        model.addAttribute("jieList",jies);
        return "user/home";
    }
}
