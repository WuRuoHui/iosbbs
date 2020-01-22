package com.wu.manager.service.impl;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.UserDTO;
import com.wu.manager.mapper.RoleMapper;
import com.wu.manager.mapper.UserGradeMapper;
import com.wu.manager.mapper.UserMapper;
import com.wu.manager.mapper.UserRoleMapper;
import com.wu.manager.pojo.*;
import com.wu.manager.service.UserService;
import com.wu.manager.utils.StringRedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 用户service实现类
 * @author: Wu
 * @create: 2020-01-11 15:42
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserGradeMapper userGradeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StringRedisService redisService;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Value(value = "${BBS.USER.GRADE}")
    private String BBS_USER_GRADE;

    @Override
    @Transactional
    public LayUIResult selectAllUserGrade() {
        //从Redis中取到用户VIP等级
        String data = redisService.getString(BBS_USER_GRADE);
        //如果用户等级不为为空，则将查询到的json格式字符串转换为List并返还
        if(!StringUtils.isEmpty(data)) {
            List<UserGrade> userGradesRedis = JsonUtils.jsonToList(data, UserGrade.class);
            return LayUIResult.ok(userGradesRedis.size(),userGradesRedis);
        }
        //如果为空，则到数据库中取，存入Redis中并返回
        List<UserGrade> userGrades = userGradeMapper.selectByExample(new UserGradeExample());
        if (userGrades != null && userGrades.size() >0) {
            redisService.setString(BBS_USER_GRADE, JsonUtils.objectToJson(userGrades));
            return LayUIResult.build(0,userGrades.size(),"查询成功",userGrades);
        }
        return LayUIResult.build(1,0,"无数据",userGrades);
    }

    /**
     * @Description: 添加用户
     * @Param: [user, roleId]
     * @return: com.wu.common.utils.LayUIResult
     * @Date: 2020/1/19
     */
    @Override
    @Transactional
    public LayUIResult insertUser(User user, Integer roleId) {
        if (user == null) {
            return LayUIResult.fail();
        }
        if (user.getId() != null) {
            int rows = userMapper.updateByPrimaryKeySelective(user);
            return rows > 0 ? LayUIResult.build(0,"更新成功"):LayUIResult.build(1,"更新失败");
        }
        //查询用户名是否存在
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            return LayUIResult.build(1,"用户名已存在");
        }
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        int rows = userMapper.insert(user);
        if (rows<=0) {
            return LayUIResult.fail();
        }
        //插入用户-角色关联表
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(roleId);
        int row = userRoleMapper.insert(userRole);
        if (row <= 0) {
            return LayUIResult.build(1,"添加失败");
        }
        return LayUIResult.build(0,"添加成功");
    }

    @Override
    public LayUIResult selectAllUser() {
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            //返回数据时将密码置为空值
            user.setPassword(null);
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            //查询用户对应的VIP等级
            UserGrade userGrade = userGradeMapper.selectByPrimaryKey(user.getVipLevel());
            userDTO.setUserGrade(userGrade);
            //查找用户对应的用户-角色关联
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUserIdEqualTo(user.getId());
            List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
            //查找对应的角色
            Role role = roleMapper.selectByPrimaryKey(userRoles.get(0).getRoleId());
            userDTO.setRole(role);
            userDTOS.add(userDTO);
        }
        if (users != null && users.size() >0) {
            return LayUIResult.ok(userDTOS.size(),userDTOS);
        }
        return LayUIResult.fail(null,null);
    }

    @Override
    public LayUIResult insertUser(UserDTO userDTO) {

        return null;
    }
}
