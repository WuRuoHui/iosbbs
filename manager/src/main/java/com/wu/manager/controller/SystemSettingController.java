package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.FriendlyLinkMapper;
import com.wu.manager.pojo.FriendlyLink;
import com.wu.manager.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 系统参数controller
 * @author: Wu
 * @create: 2020-01-08 08:30
 **/

@Controller
public class SystemSettingController {

    @Autowired
    private SystemSettingService systemSettingService;

    @RequestMapping("/page/systemSetting/basicParameter")
    public String showSystemSettingBasicParameter() {
        return "page/systemSetting/basicParameter";
    }

    @RequestMapping("/page/systemSetting/icons")
    public String showSystemSettingIcons() {
        return "page/systemSetting/icons";
    }

    @RequestMapping("/page/systemSetting/logs")
    public String showSystemSettingLogs() {
        return "page/systemSetting/logs";
    }

    @RequestMapping("/page/systemSetting/linkList")
    public String showSystemSettingLinkList() {
        return "page/systemSetting/linkList";
    }

    @RequestMapping("/page/systemSetting/linksAdd")
    public String showSystemSettingLinksAdd() {
        return "page/systemSetting/linksAdd";
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

    @DeleteMapping(value = "/systemSetting/linkList")
    @ResponseBody
    public LayUIResult deleteLinkList(@RequestBody List<Integer> linkIds) {
        LayUIResult layUIResult = systemSettingService.deleteLinkListByIds(linkIds);
        return layUIResult;
    }

    @DeleteMapping(value = "/systemSetting/linkList/{linkId}")
    @ResponseBody
    public LayUIResult deleteLinkList(@PathVariable(name = "linkId")Integer linkId) {
        LayUIResult layUIResult = systemSettingService.deleteLinkListById(linkId);
        return layUIResult;
    }
}
