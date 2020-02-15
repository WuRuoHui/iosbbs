package com.wu.bbs.controller;

import com.wu.bbs.DTO.JieDTO;
import com.wu.bbs.pojo.Jie;
import com.wu.bbs.service.JieService;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/jie/edit/{jieId}")
    public String jieDetail(@PathVariable (name = "jieId") Integer jieId, Model model) {
        JieDTO jieDTO = jieService.selectJieById(jieId);
        model.addAttribute("jie",jieDTO);
        return "jie/edit";
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

    @RequestMapping(value = "/jie/{jieId}",method = RequestMethod.GET)
    public String selectJieById(@PathVariable(name = "jieId") Integer jieId, Model model) {
        JieDTO jieDTO = jieService.selectJieById(jieId);
        model.addAttribute("jie",jieDTO);
        return "jie/detail";
    }

    @RequestMapping(value = "/jie/{jieId}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteJieById(@PathVariable(name = "jieId") Integer jieId) {
        LayUIResult layUIResult = jieService.deleteJieById(jieId);
        return layUIResult;
    }

}
