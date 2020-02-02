package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.GameMapper;
import com.wu.manager.pojo.Game;
import com.wu.manager.pojo.GameExample;
import com.wu.manager.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Game> games = gameMapper.selectByExample(new GameExample());
        if (games != null && games.size() > 0) {
            return LayUIResult.build(0,"success",games);
        }
        return LayUIResult.fail("fail");
    }
}
