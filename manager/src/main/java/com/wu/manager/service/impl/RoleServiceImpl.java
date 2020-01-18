package com.wu.manager.service.impl;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.RoleMapper;
import com.wu.manager.pojo.Role;
import com.wu.manager.pojo.RoleExample;
import com.wu.manager.service.RoleService;
import com.wu.manager.utils.StringRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: iosbbs
 * @description: 角色service实现类
 * @author: Wu
 * @create: 2020-01-18 10:02
 **/

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StringRedisService redisService;
    @Value(value = "${BBS.ROLE}")
    private String BBS_ROLE;

    @Override
    public LayUIResult selectAllRole() {
        String data = redisService.getString(BBS_ROLE);
        if (!StringUtils.isEmpty(data)) {
            List<Role> roles = JsonUtils.jsonToList(data, Role.class);
            return LayUIResult.ok(roles.size(),roles);
        }
        List<Role> roles = roleMapper.selectByExample(new RoleExample());
        if (roles != null && roles.size() >0 ) {
            redisService.setObject(BBS_ROLE,roles);
            return LayUIResult.ok(roles.size(),roles);
        }
        return LayUIResult.fail(0,null);
    }
}
