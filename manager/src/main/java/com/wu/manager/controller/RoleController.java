package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: iosbbs
 * @description: 角色controller
 * @author: Wu
 * @create: 2020-01-18 10:01
 **/

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectAllRole() {
        LayUIResult layUIResult = roleService.selectAllRole();
        return layUIResult;
    }
}