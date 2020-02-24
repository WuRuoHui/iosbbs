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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String jieIndex(@RequestParam(name = "type") String type, Model model) {
        List<JieDTO> jieListWithType = jieService.selectJieByType(type);
        model.addAttribute("jieList",jieListWithType);
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

    @RequestMapping(value = "/jie",method = RequestMethod.PUT)
    @ResponseBody
    public LayUIResult updateJie(Jie jie) {
        LayUIResult layUIResult = jieService.updateJie(jie);
        return layUIResult;
    }

    @RequestMapping("/jie/stick/{jieId}")
    @ResponseBody
    public LayUIResult updateJieStickById(@PathVariable(name = "jieId") Integer jieId,Boolean rank) {
        LayUIResult layUIResult = jieService.updateJieStickById(jieId,rank);
        return layUIResult;
    }

    @RequestMapping("/jie/boutique/{jieId}")
    @ResponseBody
    public LayUIResult updateJieBoutiqueById(@PathVariable(name = "jieId") Integer jieId,Boolean rank) {
        LayUIResult layUIResult = jieService.updateJieBoutiqueById(jieId,rank);
        return layUIResult;
    }

}
