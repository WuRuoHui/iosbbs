/**
 * @program: kefubbs
 * @description: 主页controller
 * @author: Wu
 * @create: 2019-12-10 13:31
 **/
package com.wu.bbs.controller;

import com.wu.bbs.DTO.JieDTO;
import com.wu.bbs.service.JieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
@Controller
public class IndexController {

    @Autowired
    private JieService jieService;

    @RequestMapping("/")
    public String showIndex(Model model) {
        List<JieDTO> jieListWithoutStick = jieService.selectAllJieListWithoutStick();
        List<JieDTO> jieListIfStick = jieService.selectAllJieListIfStick();
        model.addAttribute("jieList",jieListWithoutStick);
        model.addAttribute("jieListIfStick",jieListIfStick);
        return "index";
    }

}

