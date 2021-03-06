package com.wu.manager.service.impl;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.SystemParameterMapper;
import com.wu.manager.pojo.SystemParameter;
import com.wu.manager.pojo.SystemParameterExample;
import com.wu.manager.service.SystemParameterService;
import com.wu.manager.utils.StringRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Value(value = "${BBS.USER.SYSTEMPARAMETER}")
    private String BBS_USER_SYSTEMPARAMETER;
    @Autowired
    private StringRedisService stringRedisService;

    @Override
    public LayUIResult getSystemParameter() {
        //从redis中获取系统参数
        String data = stringRedisService.getString(BBS_USER_SYSTEMPARAMETER);
        //数据不为空，返回
        if (!StringUtils.isEmpty(data)) {
            SystemParameter systemParameter = JsonUtils.jsonToPojo(data, SystemParameter.class);
            return LayUIResult.ok(systemParameter);
        }
        //为空，在数据库中查找
        List<SystemParameter> systemParameters = systemParameterMapper.selectByExample(new SystemParameterExample());
        //找到后返回并存入redis中
        if (systemParameters != null && systemParameters.size() > 0) {
            stringRedisService.setObject(BBS_USER_SYSTEMPARAMETER,systemParameters.get(0));
            return LayUIResult.ok(systemParameters.get(0));
        }
        return null;
    }

    @Override
    @Transactional
    public LayUIResult insertOrUpdate(SystemParameter systemParameter) {
        List<SystemParameter> systemParameters = systemParameterMapper.selectByExample(new SystemParameterExample());
        if (systemParameter.getId() != null) {
            int rows = systemParameterMapper.updateByPrimaryKey(systemParameter);
            if (rows > 0) {
                //更新成功后将redis中对于的值置为空字符串
                stringRedisService.setString(BBS_USER_SYSTEMPARAMETER,"");
                return LayUIResult.build(0, "更新成功");
            } else {
                return LayUIResult.build(1, "更新失败");
            }
        }
        if (systemParameters != null && systemParameters.size() > 0) {
            return LayUIResult.build(1, "添加失败");
        }
        systemParameter.setId(1);
        int rows = systemParameterMapper.insert(systemParameter);
        return rows > 0 ? LayUIResult.build(0, "添加成功") : LayUIResult.build(1, "添加失败");
    }
}
