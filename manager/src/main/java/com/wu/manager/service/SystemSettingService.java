package com.wu.manager.service;

import com.wu.common.utils.LayUIResult;
import com.wu.manager.pojo.FriendlyLink;
import com.wu.manager.pojo.Passageway;

import java.util.List;

/**
 * @description: 系统设置service接口
 * @author: Wu
 * @create: 2020-01-08 09:19
 **/

public interface SystemSettingService {

    LayUIResult getAllLinkList(String search);

    LayUIResult addLinkList(FriendlyLink friendlyLink);

    LayUIResult deleteLinkListById(Integer linkId);

    LayUIResult deleteLinkListByIds(List<Integer> linkIds);

    LayUIResult selectAllPassageway(String search);

    LayUIResult insertPassageway(Passageway passageway);

    LayUIResult deletePassagewayById(Integer id);

    LayUIResult updatePassageway(Passageway passageway);

    LayUIResult deletePassagewayByIds(List<Integer> ids);
}
