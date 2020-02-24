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
    public List<JieDTO> selectAllJieListWithoutStick() {
//        List<JieDTO> jieDTOS = new ArrayList<>();
        JieExample jieExample = new JieExample();
        jieExample.createCriteria()
                .andIsStickyEqualTo(false);
        jieExample.setOrderByClause("gmt_create DESC");
        /*List<Jie> jies = jieMapper.selectByExample(jieExample);
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
        }*/
        List<JieDTO> jieDTOS = selectJieList(jieExample);
        return jieDTOS;
    }

    public List<JieDTO> selectJieList(JieExample jieExample) {
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    //将jie封装到jieDTO
    public List<JieDTO> copyJieToJieDTO(List<Jie> jies) {
        List<JieDTO> jieDTOS = new ArrayList<>();
        if (jies != null && jies.size() > 0) {
            for (Jie jie : jies) {
                JieDTO jieDTO = new JieDTO();
                BeanUtils.copyProperties(jie, jieDTO);
                if (jie.getCreator() != null) {
                    User user = userMapper.selectByPrimaryKey(jie.getCreator());
                    if (user != null) {
                        UserSimpleDTO userSimpleDTO = new UserSimpleDTO();
                        BeanUtils.copyProperties(user, userSimpleDTO);
                        //设置用户VIP等级
                        if (!StringUtils.isEmpty(user.getVipLevel())) {
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

    @Override
    public LayUIResult updateJie(Jie jie) {
        System.out.println(jie);
        if (jie == null || jie.getId() == null) {
            return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        Jie isExist = jieMapper.selectByPrimaryKey(jie.getId());
        if (isExist == null) {
            return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        //设置修改时间
        jie.setGmtModify(System.currentTimeMillis());
        int rows = jieMapper.updateByPrimaryKeySelective(jie);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateJieStickById(Integer jieId, Boolean rank) {
        if (jieId != null && rank != null ) {
            Jie jie = jieMapper.selectByPrimaryKey(jieId);
            if (jie == null) {
                return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
            }
            Jie newJie = new Jie();
            newJie.setId(jieId);
            newJie.setIsSticky(rank);
            newJie.setGmtModify(System.currentTimeMillis());
            int rows = jieMapper.updateByPrimaryKeySelective(newJie);
            if (rows > 0 ) {
                return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
            }
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateJieBoutiqueById(Integer jieId, Boolean rank) {
        if (jieId != null && rank != null ) {
            Jie jie = jieMapper.selectByPrimaryKey(jieId);
            if (jie == null) {
                return LayUIResult.build(1,CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
            }
            Jie newJie = new Jie();
            newJie.setId(jieId);
            newJie.setIsBoutique(rank);
            newJie.setGmtModify(System.currentTimeMillis());
            int rows = jieMapper.updateByPrimaryKeySelective(newJie);
            if (rows > 0 ) {
                return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
            }
        }
        return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public List<JieDTO> selectAllJieListIfStick() {
        JieExample jieExample = new JieExample();
        jieExample.createCriteria()
                .andIsStickyEqualTo(true);
        jieExample.setOrderByClause("gmt_create DESC");
        List<JieDTO> jieDTOS = selectJieList(jieExample);
        return jieDTOS;
    }

    @Override
    public List<JieDTO> selectJieByType(String type) {
        Integer integerType = getIntegerType(type);
        JieExample jieExample = new JieExample();
        jieExample.createCriteria().andColumnIdEqualTo(integerType);
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        List<JieDTO> jieDTOS = copyJieToJieDTO(jies);
        return jieDTOS;
    }

    public Integer getIntegerType(String type) {
        /*<option value="0">提问</option>
        <option value="99">分享</option>
        <option value="100">讨论</option>
        <option value="101">建议</option>
        <option value="168">公告</option>
        <option value="169">动态</option>*/
        if (type.equals("jie")) {
            return 0;
        } else if (type.equals("discussion")){
            return 100;
        } else if (type.equals("share")) {
            return 99;
        } else if (type.equals("advice")) {
            return 101;
        } else if (type.equals("notice")) {
            return 168;
        } else if (type.equals("condition")) {
            return 169;
        }
        return null;
    }
}
