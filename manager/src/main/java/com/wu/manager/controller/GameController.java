package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.Game;
import com.wu.manager.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: iosbbs
 * @description: 游戏controller
 * @author: Wu
 * @create: 2020-01-31 23:50
 **/

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/page/game/gameList")
    public String showGameListPage() {
        return "page/game/gameList";
    }

    @RequestMapping("/page/game/gameAdd")
    public String showGameAddPage() {
        return "page/game/gameAdd";
    }

    @RequestMapping(value = "/game",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult insertGame(Game game) {
        LayUIResult layUIResult = gameService.insertGame(game);
        return layUIResult;
    }
}
