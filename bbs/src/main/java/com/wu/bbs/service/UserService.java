package com.wu.bbs.service;

import com.wu.bbs.pojo.User;
import com.wu.common.utils.LayUIResult;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    LayUIResult list();

    LayUIResult insertUser(User user);
}
