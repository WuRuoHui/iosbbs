package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.FriendlyLinkMapper;
import com.wu.manager.pojo.FriendlyLink;
import com.wu.manager.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 系统参数controller
 * @author: Wu
 * @create: 2020-01-08 08:30
 **/

@Controller
public class SystemSettingController {

    @Autowired
    private SystemSettingService systemSettingService;

    @RequestMapping("/page/systemSetting/{pageName}")
    public String showSystemSettingPage(@PathVariable(name = "pageName")String pageName) {
        return "page/systemSetting/"+pageName;
    }

    @RequestMapping(value = "/systemSetting/linkList",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult getLinkList() {
        LayUIResult layUIResult = systemSettingService.getAllLinkList();
        return layUIResult;
    }

    @PostMapping(value = "/systemSetting/linkList")
    @ResponseBody
    public LayUIResult addLinkList(FriendlyLink friendlyLink) {
        LayUIResult layUIResult = systemSettingService.addLinkList(friendlyLink);
        return layUIResult;
    }
}
