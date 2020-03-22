package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.UserDTO;
import com.wu.manager.pojo.User;
import com.wu.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 用户controller
 * @author: Wu
 * @create: 2020-01-08 08:33
 **/

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "page/login/login";
    }

    @RequestMapping(value = "/page/user/userInfo/{id}",method = RequestMethod.GET)
    public String showUserInfo(@PathVariable(name = "id") Integer id, Model model) {
        UserDTO userDTO = userService.selectUserById(id);
        model.addAttribute("user",userDTO);
        return "page/user/userInfo";
    }

    @RequestMapping("/page/user/{pageName}")
    public String showUserPage(@PathVariable(name = "pageName") String pageName) {
        return "page/user/" + pageName;
    }

    @RequestMapping(value = "/user/userGrade", method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult showUserGrade() {
        LayUIResult layUIResult = userService.selectAllUserGrade();
        return layUIResult;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectUser() {
        LayUIResult layUIResult = userService.selectAllUser();
        return layUIResult;
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public LayUIResult insertUser(User user, Integer role) {
        LayUIResult layUIResult = userService.insertUser(user, role);
        return layUIResult;
    }

    @RequestMapping(value = "/user/status/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public LayUIResult updateUserStatus(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = userService.updateUserStatus(id);
        return layUIResult;
    }

    @DeleteMapping("/user")
    @ResponseBody
    public LayUIResult deleteUserByIds(@RequestBody List<Integer> userIds) {
        LayUIResult layUIResult = userService.deleteUserByIds(userIds);
        return layUIResult;
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public LayUIResult deleteUserById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = userService.deleteUserById(id);
        return layUIResult;
    }

    @PutMapping("/user/pwd")
    @ResponseBody
    public LayUIResult updateUserPassword(String oldPwd, String newPwd, Authentication authentication) {
        LayUIResult layUIResult = userService.updatePassword(oldPwd,newPwd,authentication);
        return layUIResult;
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @ResponseBody
    public LayUIResult updateUserInfo(User user,Authentication authentication) {
        LayUIResult layUIResult = userService.updateUserInfo(user,authentication);
        return layUIResult;
    }

    @RequestMapping("/userCount")
    @ResponseBody
    public LayUIResult selectUserCount() {
        LayUIResult layUIResult = userService.selectUserCount();
        return layUIResult;
    }
}