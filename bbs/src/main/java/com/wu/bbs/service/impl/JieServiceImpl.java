package com.wu.bbs.service.impl;

import com.wu.bbs.DTO.JieDTO;
import com.wu.bbs.DTO.UserSimpleDTO;
import com.wu.bbs.mapper.JieMapper;
import com.wu.bbs.mapper.UserGradeMapper;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.pojo.Jie;
import com.wu.bbs.pojo.JieExample;
import com.wu.bbs.pojo.User;
import com.wu.bbs.pojo.UserGrade;
import com.wu.bbs.service.JieService;
import com.wu.common.enums.CustomizeErrorCode;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserGradeMapper userGradeMapper;

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

    @Override
    public List<JieDTO> selectAllJieList() {
        List<JieDTO> jieDTOS = new ArrayList<>();
        List<Jie> jies = jieMapper.selectByExample(new JieExample());
        if (jies != null && jies.size() > 0) {
            for (Jie jie : jies) {
                JieDTO jieDTO = new JieDTO();
                BeanUtils.copyProperties(jie,jieDTO);
                if (jie.getCreator() != null) {
                    User user = userMapper.selectByPrimaryKey(jie.getCreator());
                    if (user != null) {
                        UserSimpleDTO userSimpleDTO = new UserSimpleDTO();
                        BeanUtils.copyProperties(user,userSimpleDTO);
                        //设置用户VIP等级
                        if (!StringUtils.isEmpty(user.getVipLevel())){
                            UserGrade userGrade = userGradeMapper.selectByPrimaryKey(Integer.valueOf(user.getVipLevel()));
                            userSimpleDTO.setUserGrade(userGrade);
                        }
                        //设置求解创建者
                        jieDTO.setCreator(userSimpleDTO);
                    }
                }
                jieDTOS.add(jieDTO);
            }
        }
        return jieDTOS;
    }

    @Override
    public JieDTO selectJieById(Integer jieId) {
        Jie jie = jieMapper.selectByPrimaryKey(jieId);
        JieDTO jieDTO = new JieDTO();
        BeanUtils.copyProperties(jie,jieDTO);
        if (jie.getCreator() != null) {
            User user = userMapper.selectByPrimaryKey(jie.getCreator());
            if (user != null) {
                UserSimpleDTO userSimpleDTO = new UserSimpleDTO();
                BeanUtils.copyProperties(user,userSimpleDTO);
                //设置用户VIP等级
                if (!StringUtils.isEmpty(user.getVipLevel())){
                    UserGrade userGrade = userGradeMapper.selectByPrimaryKey(Integer.valueOf(user.getVipLevel()));
                    userSimpleDTO.setUserGrade(userGrade);
                }
                //设置求解创建者
                jieDTO.setCreator(userSimpleDTO);
            }
        }
        return jieDTO;
    }

    @Override
    public LayUIResult deleteJieById(Integer jieId) {
        if (jieId == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
        }
        Jie jie = jieMapper.selectByPrimaryKey(jieId);
        if (jie == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        int rows = jieMapper.deleteByPrimaryKey(jieId);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }
}
