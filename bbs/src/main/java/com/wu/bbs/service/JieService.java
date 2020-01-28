package com.wu.bbs.service;

import com.wu.bbs.pojo.Jie;
import com.wu.common.utils.LayUIResult;
import org.springframework.security.core.Authentication;

/**
 * @program: iosbbs
 * @description: 求解service
 * @author: Wu
 * @create: 2020-01-24 14:50
 **/


public interface JieService {

    LayUIResult insertOrUpdate(Jie jie, Authentication authentication);
}