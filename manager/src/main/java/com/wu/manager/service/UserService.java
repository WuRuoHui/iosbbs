package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.User;

import java.util.List;

/**
 * @description: 用户service接口
 * @author: Wu
 * @create: 2020-01-11 15:42
 **/

public interface UserService {

    LayUIResult selectAllUserGrade();

    LayUIResult selectAllUser();

    LayUIResult insertUser(User user, Integer roleId);

    LayUIResult deleteUserById(Integer id);

    LayUIResult deleteUserByIds(List<Integer> userIds);

    LayUIResult selectUserCount();
}
