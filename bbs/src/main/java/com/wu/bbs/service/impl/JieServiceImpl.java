package com.wu.bbs.service.impl;

import com.wu.bbs.mapper.JieMapper;
import com.wu.bbs.pojo.Jie;
import com.wu.bbs.pojo.User;
import com.wu.bbs.service.JieService;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @program: iosbbs
 * @description: 求解service实现类
 * @author: Wu
 * @create: 2020-01-24 14:50
 **/

@Service
public class JieServiceImpl implements JieService {

    @Autowired
    private JieMapper jieMapper;

    @Override
    public LayUIResult insertOrUpdate(Jie jie, Authentication authentication) {
        if (authentication == null) return LayUIResult.build(1,"未登录无法发布，请先登录");
        User user = (User) authentication.getPrincipal();
        jie.setCreator(user.getId());
        jie.setGmtCreate(System.currentTimeMillis());
        jie.setGmtModify(System.currentTimeMillis());
        int rows = jieMapper.insertSelective(jie);
        if (rows > 0) {
            return LayUIResult.build(0,"发布成功");
        }
        return LayUIResult.build(1,"发布失败");
    }
}
