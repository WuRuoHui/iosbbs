package com.wu.bbs.service;

import com.wu.bbs.DTO.JieDTO;
import com.wu.bbs.pojo.Jie;
import com.wu.common.utils.LayUIResult;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @program: iosbbs
 * @description: 求解service
 * @author: Wu
 * @create: 2020-01-24 14:50
 **/


public interface JieService {

    LayUIResult insertOrUpdate(Jie jie, Authentication authentication);

    List<JieDTO> selectAllJieListWithoutStick();

    JieDTO selectJieById(Integer jieId);

    LayUIResult deleteJieById(Integer jieId);

    LayUIResult updateJie(Jie jie);

    LayUIResult updateJieStickById(Integer jieId, Boolean rank);

    LayUIResult updateJieBoutiqueById(Integer jieId, Boolean rank);

    List<JieDTO> selectAllJieListIfStick();

    Integer countJieByColumnIdAndStatus(Integer columnId, String status);

    List<JieDTO> selectJieByColumnIdAndCurr(Integer columnId, Integer curr);

    List<JieDTO> selectJieByColumnIdAndStatusAndCurr(Integer columnId, String status ,Integer curr);

    Integer countJieByColumnId(Integer columnId);

    List<JieDTO> selectJieByCurr(Integer curr);

    Integer countJie();

    List<JieDTO> selectJieByStatusAndCurr(String status, Integer curr);

    Integer countJieByStatus(String status);
}
