/**
 * @program: kefubbs
 * @description: 用户服务实现类
 * @author: Wu
 * @create: 2019-12-12 23:59
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.mapper.RoleMapper;
import com.wu.bbs.mapper.UserGradeMapper;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.mapper.UserRoleMapper;
import com.wu.bbs.pojo.*;
import com.wu.bbs.service.UserService;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserGradeMapper userGradeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        com.wu.bbs.pojo.User user = userMapper.selectByExample(example).get(0);
        user.getVipLevel();
        UserGrade userGrade = userGradeMapper.selectByPrimaryKey(Integer.valueOf(user.getVipLevel()));
        user.setVipLevel(userGrade.getGradeName());
        if (user == null) return null;
        List<Role> authorities = authorities(user.getId());
        user.setRoleList(authorities);
        return user;
//        return new User(user.getUsername(),user.getPassword(), authorities);
    }

    //给当前用户指定角色
    private List<Role> authorities(Integer id) {
        List<Role> authorities = new ArrayList<>();
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(id);
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
        for (UserRole userRole : userRoleList) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
            authorities.add(role);
        }
        return authorities;
    }

    @Override
    public LayUIResult list() {
        List<User> users = userMapper.selectByExample(new UserExample());
        for (User user : users) {
            user.setPassword(null);
        }
        return LayUIResult.ok(users.size(), users);
    }

    @Override
    public LayUIResult insertUser(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            return LayUIResult.build(3, "用户名不能为空");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername().trim());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            return LayUIResult.build(3, "用户已存在");
        }
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        int i = userMapper.insertSelective(user);
        if (i > 0) {
            return LayUIResult.build(0, "添加成功");
        } else {
            return LayUIResult.build(3, "添加失败");
        }
    }
}
