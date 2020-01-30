package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.Dept;

/**
 * @program: iosbbs
 * @description: 部门service
 * @author: Wu
 * @create: 2020-01-29 22:36
 **/

public interface DeptService {

    LayUIResult insertOrUpdateDept(Dept dept);

    LayUIResult selectDeptList();

    LayUIResult deleteDeptById(Integer id);
}
