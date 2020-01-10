package com.wu.manager.controller;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.SystemParameter;
import com.wu.manager.service.SystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @description: 系统参数设置
 * @author: Wu
 * @create: 2020-01-10 13:10
 **/

@Controller
public class SystemParameterController {

    @Autowired
    private SystemParameterService systemParameterService;

    /**
     * @Description: 系统参数页面跳转
     * @Param: []
     * @return: java.lang.String
     * @Author: wu
     * @Date: 2020/1/11
     */
    @GetMapping("/page/systemSetting/basicParameter")
    public String showSystemSettingBasicParameter() throws IOException {
        return "page/systemSetting/basicParameter";
    }

    /**
     * @Description: 获取系统参数
     * @Param: []
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/11
     */
    @GetMapping("/systemSetting/basicParameter")
    @ResponseBody
    public LayUIResult getSystemSettingBasicParameter() {
        LayUIResult layUIResult = systemParameterService.getSystemParameter();
        return layUIResult;
    }

    /**
     * @Description: 系统参数插入或者更新，根据参数中是否有ID判断是插入或者更新
     * @Param: [systemParameter]
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/11
     */
    @PostMapping("/systemSetting/basicParameter")
    @ResponseBody
    public LayUIResult insertSystemSettingBasicParameter(@RequestBody SystemParameter systemParameter) {
        LayUIResult layUIResult = systemParameterService.insertOrUpdate(systemParameter);
        return layUIResult;
    }
}
