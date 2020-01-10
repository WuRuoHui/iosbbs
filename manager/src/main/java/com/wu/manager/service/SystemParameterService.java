package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.SystemParameter;

/**
 * @description: 系统参数service
 * @author: Wu
 * @create: 2020-01-10 13:18
 **/

public interface SystemParameterService {
    LayUIResult getSystemParameter();
    LayUIResult insertOrUpdate(SystemParameter systemParameter);
}
