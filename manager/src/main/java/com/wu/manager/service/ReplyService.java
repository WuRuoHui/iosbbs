package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;

import java.util.List;

/**
 * @program: iosbbs
 * @description: 回复服务接口
 * @author: Wu
 * @create: 2020-03-10 09:10
 **/

public interface ReplyService {

    LayUIResult selectReplys(Integer page, Integer limit);

    LayUIResult deleteReplyById(Integer id);

    LayUIResult deleteReplyByIds(List<Integer> ids);
}
