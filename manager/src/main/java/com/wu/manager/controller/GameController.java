package com.wu.manager.controller;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.Game;
import com.wu.manager.pojo.GameContact;
import com.wu.manager.pojo.GameDownload;
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
    public LayUIResult selectAllGames(String nameSearch,Integer page, Integer limit) {
        LayUIResult layUIResult = gameService.selectAllGames(nameSearch,page,limit);
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

    @RequestMapping("/page/game/gameDownloadList")
    public String showGameDownloadListPage(){
        return "page/gameDownload/gameDownloadList";
    }

    @RequestMapping("/page/game/gameDownloadAdd")
    public String showGameDownloadAddPage() {
        return "page/gameDownload/gameDownloadAdd";
    }

    @RequestMapping(value = "/gameDownload",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult insertOrUpdateGameDownload(GameDownload gameDownload) {
        LayUIResult layUIResult = gameService.insertOrUpdateGameDownload(gameDownload);
        return layUIResult;
    }

    @RequestMapping(value = "/gameDownloads",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectAllGameDownload(String nameSearch,Integer page, Integer limit) {
        LayUIResult layUIResult = gameService.selectAllGameDownloads(nameSearch,page,limit);
        return layUIResult;
    }

    @RequestMapping(value = "/gameDownload/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteGameDownloadById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = gameService.deleteGameDownloadById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/gameDownloads",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteGameDownloadByIds(@RequestBody List<Integer> gameDownloadIds) {
        LayUIResult layUIResult = gameService.deleteGameDownloadByIds(gameDownloadIds);
        return layUIResult;
    }

    @RequestMapping("/page/gameContact/gameContactList")
    public String showGameContactList(){
        return "page/gameContact/gameContactList";
    }

    @RequestMapping("/page/gameContact/gameContactAdd")
    public String showGameContactAdd(){
        return "page/gameContact/gameContactAdd";
    }

    @RequestMapping("/page/gameContact/gameContactUpdate")
    public String showGameContactUpdate(){
        return "page/gameContact/gameContactUpdate";
    }

    @RequestMapping(value = "/gameContact",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult insertGameContact(GameContact gameContact) {
        LayUIResult layUIResult = gameService.insertGameContact(gameContact);
        return layUIResult;
    }

    @RequestMapping(value = "/gameContacts",method = RequestMethod.GET)
    @ResponseBody
    public LayUIResult selectAllGameContacts() {
        LayUIResult layUIResult = gameService.selectAllGameContacts();
        return layUIResult;
    }
}
