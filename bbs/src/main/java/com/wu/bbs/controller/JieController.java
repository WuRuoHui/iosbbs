package com.wu.bbs.controller;

import com.wu.bbs.pojo.Jie;
import com.wu.bbs.service.JieService;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
public class JieController {

    @Autowired
    private JieService jieService;

    @RequestMapping("/jie/add")
    public String jieAdd() {
        return "jie/add";
    }

    @RequestMapping("/jie/detail")
    public String jieDetail() {
        return "jie/detail";
    }

    @RequestMapping("/jie/index")
    public String jieIndex() {
        return "jie/index";
    }

    @RequestMapping(value = "/jie",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult addJie(Jie jie, Authentication authentication) {
        LayUIResult layUIResult = jieService.insertOrUpdate(jie,authentication);
        return layUIResult;
    }
}
