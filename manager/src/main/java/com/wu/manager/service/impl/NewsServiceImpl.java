package com.wu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.wu.common.enums.CustomizeErrorCode;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.JieDTO;
import com.wu.manager.dto.UserSimpleDTO;
import com.wu.manager.mapper.GameMapper;
import com.wu.manager.mapper.JieMapper;
import com.wu.manager.mapper.UserMapper;
import com.wu.manager.pojo.Game;
import com.wu.manager.pojo.Jie;
import com.wu.manager.pojo.JieExample;
import com.wu.manager.pojo.User;
import com.wu.manager.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: iosbbs
 * @description: news接口实现类
 * @author: Wu
 * @create: 2020-02-27 15:10
 **/

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private JieMapper jieMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GameMapper gameMapper;

    @Override
    public LayUIResult selectAllNews(String search) {
        JieExample jieExample = new JieExample();
        if (search != null && !StringUtils.isEmpty(search.trim())) {
            jieExample.createCriteria()
                    .andTitleLike("%" + search.trim() + "%");
        }
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        if (jies != null && jies.size() > 0) {
            List<JieDTO> jieDTOS = new ArrayList<>();
            for (Jie jie : jies) {
                JieDTO jieDTO = new JieDTO();
                BeanUtils.copyProperties(jie, jieDTO);
                if (jie.getCreator() != null) {
                    User user = userMapper.selectByPrimaryKey(jie.getCreator());
                    UserSimpleDTO userSimpleDTO = new UserSimpleDTO();
                    BeanUtils.copyProperties(user, userSimpleDTO);
                    jieDTO.setCreator(userSimpleDTO);
                    if (jie.getProjectId() != null) {
                        Game game = gameMapper.selectByPrimaryKey(jie.getProjectId());
                        jieDTO.setProject(game);
                    }
                }
                jieDTOS.add(jieDTO);
            }
            return LayUIResult.build(0, jieDTOS.size(), CustomizeErrorCode.SELECT_DATA_SUCCESS.getMessage(), jieDTOS);
        }
        return LayUIResult.build(1, CustomizeErrorCode.SELECT_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult deleteNewsById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        Jie jie = jieMapper.selectByPrimaryKey(id);
        if (jie == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        int rows = jieMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult deleteNewsByIds(List<Integer> ids) {
        if (ids == null || ids.size() < 1) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        for (Integer id : ids) {
            Jie jie = jieMapper.selectByPrimaryKey(id);
            if (jie == null) {
                return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
            }
            int rows = jieMapper.deleteByPrimaryKey(id);
            if (rows < 1) {
                return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
            }
        }
        return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
    }

    @Override
    public LayUIResult selectRecentNews() {
        PageHelper.startPage(1, 5);
        JieExample jieExample = new JieExample();
        jieExample.setOrderByClause("gmt_create DESC");
        List<Jie> jies = jieMapper.selectByExample(jieExample);
        return LayUIResult.build(0, CustomizeErrorCode.SELECT_DATA_SUCCESS.getMessage(), jies);
    }

    @Override
    public LayUIResult updateStickyById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        Jie jie = jieMapper.selectByPrimaryKey(id);
        if (jie == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        Jie jieNew = new Jie();
        jieNew.setId(id);
        jieNew.setIsSticky(!jie.getIsSticky());
        int rows = jieMapper.updateByPrimaryKeySelective(jieNew);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateBoutiqueById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        Jie jie = jieMapper.selectByPrimaryKey(id);
        if (jie == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        Jie jieNew = new Jie();
        jieNew.setId(id);
        jieNew.setIsBoutique(!jie.getIsBoutique());
        int rows = jieMapper.updateByPrimaryKeySelective(jieNew);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult updateClosedById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        Jie jie = jieMapper.selectByPrimaryKey(id);
        if (jie == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        Jie jieNew = new Jie();
        jieNew.setId(id);
        jieNew.setIsClosed(!jie.getIsClosed());
        int rows = jieMapper.updateByPrimaryKeySelective(jieNew);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult selectNewsWithPaging(String search, Integer page, Integer limit) {
        if (page != null && limit != null) {
            PageHelper.startPage(page, limit);
        }
        LayUIResult layUIResult = selectAllNews(search);
        JieExample jieExample = new JieExample();
        if (search != null && !StringUtils.isEmpty(search.trim())) {
            jieExample.createCriteria()
                    .andTitleLike("%" + search.trim() + "%");
        }
        long count = jieMapper.countByExample(jieExample);
        layUIResult.setCount((int) count);
        return layUIResult;
    }
}
