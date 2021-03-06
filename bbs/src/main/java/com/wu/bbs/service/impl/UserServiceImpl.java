/**
 * @program: kefubbs
 * @description: 用户服务实现类
 * @author: Wu
 * @create: 2019-12-12 23:59
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.DTO.UserDTO;
import com.wu.bbs.mapper.RoleMapper;
import com.wu.bbs.mapper.UserGradeMapper;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.mapper.UserRoleMapper;
import com.wu.bbs.pojo.*;
import com.wu.bbs.service.UserService;
import com.wu.common.enums.CustomizeErrorCode;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        com.wu.bbs.pojo.User user = userMapper.selectByExample(example).get(0);
        user.getVipLevel();
        UserGrade userGrade = userGradeMapper.selectByPrimaryKey(user.getVipLevel());
        user.setVipName(userGrade.getGradeName());
        if (user == null) return null;
        List<Role> authorities = authorities(user.getId());
        user.setRoleList(authorities);
        return user;
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

    @Override
    public UserDTO selectUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(null);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        UserGrade userGrade = userGradeMapper.selectByPrimaryKey(Integer.valueOf(user.getVipLevel()));
        userDTO.setUserGrade(userGrade);
        return userDTO;
    }

    @Override
    public LayUIResult UpdateUserInfo(Authentication authentication, User user) {
        User u = (User) authentication.getPrincipal();
        User userIsExist = userMapper.selectByPrimaryKey(u.getId());
        if (userIsExist == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        user.setId(u.getId());
        int rows = userMapper.updateByPrimaryKeySelective(user);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateUserPassword(Authentication authentication, String nowpass, String pass, String repass) {
        if (StringUtils.isEmpty(nowpass) || StringUtils.isEmpty(pass) || StringUtils.isEmpty(repass)) {
            return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        if (!pass.equals(repass)) {
            return LayUIResult.build(1,"两次密码不一致");
        }
        User user = (User) authentication.getPrincipal();
        if (!passwordEncoder.matches(nowpass, user.getPassword())) {
            return LayUIResult.build(1,"密码错误");
        }
        User userNew = new User();
        userNew.setId(user.getId());
        userNew.setPassword(passwordEncoder.encode(pass));
        int rows = userMapper.updateByPrimaryKeySelective(userNew);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

}
