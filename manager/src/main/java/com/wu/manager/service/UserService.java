package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.UserDTO;
import com.wu.manager.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @description: 用户service接口
 * @author: Wu
 * @create: 2020-01-11 15:42
 **/

public interface UserService extends UserDetailsService {

    LayUIResult selectAllUserGrade();

    LayUIResult selectAllUser(String search, Integer page, Integer limit);

    LayUIResult insertUser(User user, Integer roleId);

    LayUIResult deleteUserById(Integer id);

    LayUIResult deleteUserByIds(List<Integer> userIds);

    LayUIResult selectUserCount();

    UserDTO selectUserById(Integer id);

    LayUIResult updatePassword(String oldPwd, String newPwd, Authentication authentication);

    LayUIResult updateUserInfo(User user, Authentication authentication);

    LayUIResult updateUserStatus(Integer id);
}
