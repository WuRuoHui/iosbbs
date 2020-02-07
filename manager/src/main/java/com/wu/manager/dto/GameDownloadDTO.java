package com.wu.manager.dto;

import com.wu.manager.pojo.Game;
import lombok.Data;

/**
 * @program: iosbbs
 * @description: 游戏下载方式数据传输类
 * @author: Wu
 * @create: 2020-02-06 08:25
 **/

@Data

public class GameDownloadDTO {
    private Integer id;

    private Game game;

    private String url;

    private Integer mix;
}
