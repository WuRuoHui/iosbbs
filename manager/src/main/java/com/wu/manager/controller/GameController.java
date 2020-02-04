package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.Game;
import com.wu.manager.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/mainGames",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectMainGames() {
        LayUIResult layUIResult = gameService.selectMainGames();
        return layUIResult;
    }

    @RequestMapping(value = "/games",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectAllGames() {
        LayUIResult layUIResult = gameService.selectAllGames();
        return layUIResult;
    }

    @RequestMapping(value = "/game/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteGameById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = gameService.deleteGameById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/games",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteGamesByIds(@RequestBody List<Integer> ids) {
        LayUIResult layUIResult = gameService.deleteGamesByIds(ids);
        return layUIResult;
    }
}
