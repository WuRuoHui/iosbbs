package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;

/**
 * @description: 用户service接口
 * @author: Wu
 * @create: 2020-01-11 15:42
 **/

public interface UserService {

    LayUIResult selectAllUserGrade();

    LayUIResult selectAllUser();
}
