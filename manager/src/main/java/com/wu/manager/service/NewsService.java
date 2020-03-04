package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;

import java.util.List;

/**
 * @program: iosbbs
 * @description: news接口
 * @author: Wu
 * @create: 2020-02-27 15:10
 **/

public interface NewsService {

    LayUIResult selectAllNews();

    LayUIResult deleteNewsById(Integer id);

    LayUIResult deleteNewsByIds(List<Integer> ids);

    LayUIResult selectRecentNews();

    LayUIResult updateStickyById(Integer id);

    LayUIResult updateBoutiqueById(Integer id);

    LayUIResult updateClosedById(Integer id);

    LayUIResult selectNewsWithPaging(Integer page, Integer limit);
}
