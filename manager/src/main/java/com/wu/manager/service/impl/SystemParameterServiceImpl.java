package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.SystemParameterMapper;
import com.wu.manager.pojo.SystemParameter;
import com.wu.manager.pojo.SystemParameterExample;
import com.wu.manager.service.SystemParameterService;
import com.wu.manager.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 系统参数service实现类
 * @author: Wu
 * @create: 2020-01-10 13:18
 **/

@Service
public class SystemParameterServiceImpl implements SystemParameterService {

    @Autowired
    private SystemParameterMapper systemParameterMapper;

    @Override
    public LayUIResult getSystemParameter() {
        List<SystemParameter> systemParameters = systemParameterMapper.selectByExample(new SystemParameterExample());
        if (systemParameters != null && systemParameters.size() > 0) {
            return LayUIResult.ok(systemParameters.get(0));
        }
        return null;
    }
}
