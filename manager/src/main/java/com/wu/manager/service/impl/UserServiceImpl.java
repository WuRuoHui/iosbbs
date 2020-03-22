package com.wu.manager.service.impl;

import com.wu.common.enums.CustomizeErrorCode;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value(value = "${BBS.USER.GRADE}")
    private String BBS_USER_GRADE;
    @Value(value = "BBS.USER")
    private String BBS_USER;
    @Value(value = "BBS.USERCOUNT")
    private String BBS_USER_COUNT;

    @Override
    @Transactional
    public LayUIResult selectAllUserGrade() {
        //从Redis中取到用户VIP等级
        String data = redisService.getString(BBS_USER_GRADE);
        //如果用户等级不为为空，则将查询到的json格式字符串转换为List并返还
        if (!StringUtils.isEmpty(data)) {
            List<UserGrade> userGradesRedis = JsonUtils.jsonToList(data, UserGrade.class);
            return LayUIResult.ok(userGradesRedis.size(), userGradesRedis);
        }
        //如果为空，则到数据库中取，存入Redis中并返回
        List<UserGrade> userGrades = userGradeMapper.selectByExample(new UserGradeExample());
        if (userGrades != null && userGrades.size() > 0) {
            redisService.setString(BBS_USER_GRADE, JsonUtils.objectToJson(userGrades));
            return LayUIResult.build(0, userGrades.size(), "查询成功", userGrades);
        }
        return LayUIResult.build(1, 0, "无数据", userGrades);
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
            //如果不更新密码，则设置密码为空，防止更新时清空密码
            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(null);
            }
            user.setGmtModified(System.currentTimeMillis());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            int rows = userMapper.updateByPrimaryKeySelective(user);
            //更新用户角色表
            //判断用户角色表中是否存在对应的关系
            UserRoleExample example = new UserRoleExample();
            List<UserRole> userRoles = userRoleMapper.selectByExample(example);
            //如果存在，更新角色-用户关系表
            if (userRoles != null && userRoles.size() > 0) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(roleId);
                UserRoleExample example1 = new UserRoleExample();
                example1.createCriteria()
                        .andUserIdEqualTo(user.getId());
                userRoleMapper.updateByExampleSelective(userRole, example1);
            } else {
                //如果不存在
                //插入用户-角色关联表
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(roleId);
                userMapper.insert(user);
            }
            System.out.println(user);
            System.out.println(rows);
            if (rows > 0) {
                return LayUIResult.build(0, "更新成功");
            }
            return LayUIResult.build(1, "更新失败");
        }
        //查询用户名是否存在
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            return LayUIResult.build(1, "用户名已存在");
        }
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int rows = userMapper.insert(user);
        if (rows <= 0) {
            return LayUIResult.fail();
        }
        //插入用户-角色关联表
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(roleId);
        int row = userRoleMapper.insert(userRole);
        if (row <= 0) {
            return LayUIResult.build(1, "添加失败");
        }
        redisService.deleteHash(BBS_USER, BBS_USER_COUNT);
        return LayUIResult.build(0, "添加成功");
    }

    /**
     * @Description: 删除单个用户
     * @Param: [id]
     * @return: com.wu.common.utils.LayUIResult
     * @Date: 2020/1/26
     */
    @Override
    public LayUIResult deleteUserById(Integer id) {
        if (id == null) return LayUIResult.fail();
        int rows = userMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            deleteUserRoleById(id);
            return LayUIResult.build(0, "删除成功");
        }
        return LayUIResult.fail();
    }

    /**
     * @Description: 批量删除用户
     * @Param: [userIds]
     * @return: com.wu.common.utils.LayUIResult
     * @Date: 2020/1/26
     */
    @Override
    @Transactional
    public LayUIResult deleteUserByIds(List<Integer> userIds) {
        if (userIds == null || userIds.size() < 1) {
            LayUIResult.fail("请选择需要删除的用户");
        }
        for (Integer id : userIds) {
            int rows = userMapper.deleteByPrimaryKey(id);
            if (rows < 1) {
                return LayUIResult.fail("删除失败");
            }
            deleteUserById(id);
        }
        redisService.deleteHash(BBS_USER, BBS_USER_COUNT);
        return LayUIResult.build(0, "批量删除成功");
    }

    @Override
    /**
    * @Description: 查询总用户数
    * @Param: []
    * @return: com.wu.common.utils.LayUIResult
    * @Date: 2020/1/28
    */
    public LayUIResult selectUserCount() {
        String result = (String) redisService.getHash(BBS_USER, BBS_USER_COUNT);
        if (!StringUtils.isEmpty(result)) {
            return LayUIResult.ok(Integer.valueOf(result), null);
        }
        Long count = userMapper.countByExample(new UserExample());
        redisService.putHash(BBS_USER, BBS_USER_COUNT, String.valueOf(count));
        return LayUIResult.ok(count.intValue(), null);
    }

    @Override
    public UserDTO selectUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(null);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        Role role = roleMapper.selectByPrimaryKey(userRoles.get(0).getRoleId());
        userDTO.setRole(role);
        return userDTO;
    }

    @Override
    public LayUIResult updatePassword(String oldPwd, String newPwd, Authentication authentication) {
        if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd)) {
            return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        if (oldPwd.trim().equals(newPwd.trim())) {
            return LayUIResult.build(1,"输入的新旧密码一致，请重新核对");
        }
        User user = (User) authentication.getPrincipal();
        User userFromDB = userMapper.selectByPrimaryKey(user.getId());
        if (!passwordEncoder.matches(oldPwd,user.getPassword())){
            return LayUIResult.build(1,"密码错误");
        }
        User newUser = new User();
        newUser.setId(userFromDB.getId());
        newUser.setPassword(passwordEncoder.encode(newPwd));
        int rows = userMapper.updateByPrimaryKeySelective(newUser);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateUserInfo(User user, Authentication authentication) {
        User userFromAuthority = (User) authentication.getPrincipal();
        User userFromDB = userMapper.selectByPrimaryKey(userFromAuthority.getId());
        if (userFromDB == null) {
            return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        user.setId(userFromAuthority.getId());
        int rows = userMapper.updateByPrimaryKeySelective(user);
        if (rows > 0) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    @Transactional
    public LayUIResult updateUserStatus(Integer id) {
        if (id == null) {
            return LayUIResult.build(1,CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        User userNew = new User();
        userNew.setId(id);
        userNew.setStatus(!user.getStatus());
        int rows = userMapper.updateByPrimaryKeySelective(userNew);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
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
            BeanUtils.copyProperties(user, userDTO);
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
        if (users != null && users.size() > 0) {
            return LayUIResult.ok(userDTOS.size(), userDTOS);
        }
        return LayUIResult.fail(null, null);
    }

    /**
     * @Description: 根据userId删除用户-角色关联表
     * @Param: [id]
     * @return: void
     * @Date: 2020/1/26
     */
    public void deleteUserRoleById(Integer id) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(id);
        userRoleMapper.deleteByExample(userRoleExample);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        com.wu.manager.pojo.User user = userMapper.selectByExample(example).get(0);
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

}
