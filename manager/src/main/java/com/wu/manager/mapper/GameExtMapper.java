package com.wu.manager.mapper;

import java.util.List;

/**
 * @program: iosbbs
 * @description: 游戏下载方式扩展mapper
 * @author: Wu
 * @create: 2020-02-07 22:49
 **/

public interface GameExtMapper {
    List<Integer> selectIdsByNameSearch(String nameSearch);
}
