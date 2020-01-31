package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.Dept;
import com.wu.manager.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: iosbbs
 * @description: 部门controller
 * @author: Wu
 * @create: 2020-01-29 21:09
 **/

@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/page/dept/deptList")
    public String showDeptList() {
        return "page/dept/deptList";
    }

    @RequestMapping("//page/dept/deptAdd")
    public String showDeptAdd() {
        return "page/dept/deptAdd";
    }

    @RequestMapping(value = "/dept",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult insertOrUpdateDept(Dept dept) {
        LayUIResult layUIResult = deptService.insertOrUpdateDept(dept);
        return layUIResult;
    }

    @RequestMapping("/depts")
    @ResponseBody
    public LayUIResult selectDeptList() {
        LayUIResult layUIResult = deptService.selectDeptList();
        return layUIResult;
    }

    @RequestMapping(value = "/dept/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public  LayUIResult deleteDeptById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = deptService.deleteDeptById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/depts",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteDeptByIds(@RequestBody List<Integer> userIds) {
        LayUIResult layUIResult = deptService.deleteDeptByIds(userIds);
        return layUIResult;
    }
}
