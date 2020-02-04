package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.GameDTO;
import com.wu.manager.enums.CustomizeErrorCode;
import com.wu.manager.mapper.DeptMapper;
import com.wu.manager.mapper.GameMapper;
import com.wu.manager.pojo.Dept;
import com.wu.manager.pojo.Game;
import com.wu.manager.pojo.GameExample;
import com.wu.manager.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: iosbbs
 * @description: 游戏service实现类
 * @author: Wu
 * @create: 2020-02-01 00:10
 **/

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public LayUIResult insertGame(Game game) {
        //判断游戏名是否已存在
        GameExample gameExample = new GameExample();
        GameExample.Criteria criteria = gameExample.createCriteria().andNameEqualTo(game.getName());
        //如果为更新操作，则当前更新行应该排除
        if (game.getId() != null) {
            criteria.andIdNotEqualTo(game.getId());
        }
        List<Game> games = gameMapper.selectByExample(gameExample);
        if (games != null && games.size() > 0) {
            return LayUIResult.build(1,CustomizeErrorCode.GAME_ALREADY_EXIST.getMessage());
        }
        //当id不为空时为更新操作
        if (game.getId() != null) {
            game.setGmtModify(System.currentTimeMillis());
            int rows = gameMapper.updateByPrimaryKeySelective(game);
            if (rows > 0) {
                return LayUIResult.build(0,CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
            }
            return LayUIResult.build(1,CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        //插入新数据操作
        game.setGmtCreate(System.currentTimeMillis());
        game.setGmtModify(System.currentTimeMillis());
        int rows = gameMapper.insertSelective(game);
        if (rows > 0) {
            return LayUIResult.build(0,"添加成功！");
        }
        return LayUIResult.fail("添加失败");
    }

    @Override
    public LayUIResult selectMainGames() {
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andIsParentEqualTo(true);
        List<Game> mainGames = gameMapper.selectByExample(gameExample);
        if (mainGames != null && mainGames.size() > 0) {
            return LayUIResult.build(0,"success",mainGames);
        }
        return LayUIResult.fail("fail");
    }

    @Override
    public LayUIResult selectAllGames() {
        List<GameDTO> gameDTOS = new ArrayList<>();
        List<Game> games = gameMapper.selectByExample(new GameExample());
        if (games != null && games.size() > 0) {
            for (Game game : games) {
                GameDTO gameDTO = new GameDTO();
                BeanUtils.copyProperties(game,gameDTO);
                Dept dept = deptMapper.selectByPrimaryKey(game.getDeptId());
                gameDTO.setDept(dept);
                if (!game.getIsParent() && game.getParentId() != null) {
                    Game parentGame = gameMapper.selectByPrimaryKey(game.getParentId());
                    gameDTO.setParent(parentGame);
                }
                gameDTOS.add(gameDTO);
            }
            return LayUIResult.build(0,games.size(),"success",gameDTOS);
        }
        return LayUIResult.fail("fail");
    }

    @Override
    public LayUIResult deleteGameById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1,CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        Game game = gameMapper.selectByPrimaryKey(id);
        if (game == null) {
            return LayUIResult.build(1, CustomizeErrorCode.GAME_NOT_FOUND.getMessage());
        }
        int rows = gameMapper.deleteByPrimaryKey(id);
        if (rows > 0 ) {
            return LayUIResult.build(0,CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }

    @Override
    @Transactional
    public LayUIResult deleteGamesByIds(List<Integer> ids) {
        if (ids == null || ids.size() < 1) {
            return LayUIResult.build(1,CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        for (Integer id : ids) {
            int rows = gameMapper.deleteByPrimaryKey(id);
            if (rows < 1 ) {
                return LayUIResult.build(1,CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
            }
        }
        return LayUIResult.build(0,CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
    }
}
