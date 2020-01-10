package com.wu.manager.controller;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.SystemParameter;
import com.wu.manager.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 系统参数设置
 * @author: Wu
 * @create: 2020-01-10 13:10
 **/

@Controller
public class SystemParameterController {

    @Autowired
    private SystemParameterService systemParameterService;

    @GetMapping("/page/systemSetting/basicParameter")
    public String showSystemSettingBasicParameter() throws IOException {
        return "page/systemSetting/basicParameter";
    }

    @GetMapping("/systemSetting/basicParameter")
    @ResponseBody
    public LayUIResult getSystemSettingBasicParameter(){
        LayUIResult layUIResult = systemParameterService.getSystemParameter();
        return layUIResult;
    }
}
