package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.FriendlyLink;
import com.wu.manager.pojo.Passageway;
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

    @CrossOrigin(origins = "http://127.0.0.1:8080",maxAge = 3600)
    @RequestMapping(value = "/systemSetting/linkList",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult getLinkList(String search) {
        LayUIResult layUIResult = systemSettingService.getAllLinkList(search);
        return layUIResult;
    }

    /**
     * @Description: 插入一个友链
     * @Param: [friendlyLink]
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/10
     */
    @PostMapping(value = "/systemSetting/linkList")
    @ResponseBody
    public LayUIResult addLinkList(FriendlyLink friendlyLink) {
        LayUIResult layUIResult = systemSettingService.addLinkList(friendlyLink);
        return layUIResult;
    }

    @DeleteMapping(value = "/systemSetting/linkList")
    /**
    * @Description: 根据id集合删除多个友链
    * @Param: [linkIds]
    * @return: com.wu.common.utils.LayUIResult
    * @Author: wu
    * @Date: 2020/1/10
    */
    @ResponseBody
    public LayUIResult deleteLinkList(@RequestBody List<Integer> linkIds) {
        LayUIResult layUIResult = systemSettingService.deleteLinkListByIds(linkIds);
        return layUIResult;
    }

    /**
     * @Description: 删除一个友链
     * @Param: [linkId]
     * @return: com.wu.common.utils.LayUIResult
     * @Author: wu
     * @Date: 2020/1/10
     */
    @DeleteMapping(value = "/systemSetting/linkList/{linkId}")
    @ResponseBody
    public LayUIResult deleteLinkList(@PathVariable(name = "linkId")Integer linkId) {
        LayUIResult layUIResult = systemSettingService.deleteLinkListById(linkId);
        return layUIResult;
    }

    @RequestMapping("/page/systemSetting/passageway")
    public String showPassagewayPage() {
        return "page/passageway/passagewayList";
    }

    @RequestMapping("/page/systemSetting/passagewayAdd")
    public String showPassagewayAddPage() {
        return "page/passageway/passagewayAdd";
    }

    @RequestMapping("/page/systemSetting/passagewayUpdate")
    public String showPassagewayUpdatePage() {
        return "page/passageway/passagewayUpdate";
    }

    @RequestMapping(value = "/passageways",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectAllPassageway() {
        LayUIResult layUIResult = systemSettingService.selectAllPassageway();
        return layUIResult;
    }

    @RequestMapping(value = "/systemSetting/passageway",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult insertPassageway(Passageway passageway) {
        LayUIResult layUIResult = systemSettingService.insertPassageway(passageway);
        return layUIResult;
    }
}
