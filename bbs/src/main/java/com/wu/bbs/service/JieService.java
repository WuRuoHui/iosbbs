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

    List<JieDTO> selectJieByType(String type);

    List<JieDTO> selectQuizJie();

    List<JieDTO> selectShareJie();

    List<JieDTO> selectDiscussionJie();

    List<JieDTO> selectAdviceJie();

    List<JieDTO> selectNoticeJie();

    List<JieDTO> selectConditionJie();

    List<JieDTO> selectQuizJieWithStatus(String status);

    List<JieDTO> selectShareJieWithStatus(String status);

    List<JieDTO> selectDiscussionJieWithStatus(String status);

    List<JieDTO> selectAdviceJieWithStatus(String status);

    List<JieDTO> selectNoticeJieWithStatus(String status);

    List<JieDTO> selectConditionJieWithStatus(String status);
}
