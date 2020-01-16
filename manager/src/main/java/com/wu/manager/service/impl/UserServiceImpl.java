package com.wu.manager.service.impl;

import com.wu.common.utils.JsonUtils;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.UserGradeMapper;
import com.wu.manager.mapper.UserMapper;
import com.wu.manager.pojo.User;
import com.wu.manager.pojo.UserExample;
import com.wu.manager.pojo.UserGrade;
import com.wu.manager.pojo.UserGradeExample;
import com.wu.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private StringRedisTemplate redisTemplate;
    @Value(value = "${BBS.USER.GRADE}")
    private String BBS_USER_GRADE;
    @Value(value = "${BBS.USER.GRADE.COUNT}")
    private String BBS_USER_GRADE_COUNT;
    @Value(value = "${BBS.USER.GRADE.DATA}")
    private String BBS_USER_GRADE_DATA;

    @Override
    @Transactional
    public LayUIResult selectAllUserGrade() {
        String data = (String) redisTemplate.opsForValue().get(BBS_USER_GRADE);
        if(!StringUtils.isEmpty(data) /*&& StringUtils.isEmpty()*/) {
            List<UserGrade> userGradesRedis = JsonUtils.jsonToList(data, UserGrade.class);
            System.out.println("redis");
            return LayUIResult.ok(userGradesRedis.size(),userGradesRedis);
        }
        List<UserGrade> userGrades = userGradeMapper.selectByExample(new UserGradeExample());
        if (userGrades != null && userGrades.size() >0) {
            System.out.println("mysql");
            redisTemplate.opsForValue().set(BBS_USER_GRADE, JsonUtils.objectToJson(userGrades));
            return LayUIResult.build(0,userGrades.size(),"查询成功",userGrades);
        }
        return LayUIResult.build(1,0,"无数据",userGrades);
    }

    @Override
    public LayUIResult selectAllUser() {
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() >0) {
            return LayUIResult.ok(users.size(),users);
        }
        return LayUIResult.fail(null,null);
    }
}
