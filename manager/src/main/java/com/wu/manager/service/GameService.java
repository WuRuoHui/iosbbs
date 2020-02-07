package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.Game;
import com.wu.manager.pojo.GameDownload;

import java.util.List;

/**
 * @program: iosbbs
 * @description: 游戏service接口
 * @author: Wu
 * @create: 2020-02-01 00:09
 **/

public interface GameService {

    LayUIResult insertGame(Game game);

    LayUIResult selectMainGames();

    LayUIResult selectAllGames(String nameSearch, Integer page, Integer limit);

    LayUIResult deleteGameById(Integer id);

    LayUIResult deleteGamesByIds(List<Integer> ids);

    LayUIResult insertOrUpdateGameDownload(GameDownload gameDownload);

    LayUIResult selectAllGameDownloads(String nameSearch, Integer page, Integer limit);

    LayUIResult deleteGameDownloadById(Integer id);

    LayUIResult deleteGameDownloadByIds(List<Integer> gameDownloadIds);
}
