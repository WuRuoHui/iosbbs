package com.wu.manager.service.impl;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.mapper.GameMapper;
import com.wu.manager.pojo.Game;
import com.wu.manager.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
