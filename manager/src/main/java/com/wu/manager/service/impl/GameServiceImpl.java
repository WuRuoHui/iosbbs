package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.GameDTO;
import com.wu.manager.mapper.DeptMapper;
import com.wu.manager.mapper.GameMapper;
import com.wu.manager.pojo.Dept;
import com.wu.manager.pojo.Game;
import com.wu.manager.pojo.GameExample;
import com.wu.manager.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
