package com.wu.bbs.service;

import com.wu.bbs.DTO.UserDTO;
import com.wu.bbs.pojo.User;
import com.wu.common.utils.LayUIResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    LayUIResult list();

    LayUIResult insertUser(User user);

    UserDTO selectUserById(Integer id);

    LayUIResult UpdateUserInfo(Authentication authentication, User user);

    LayUIResult updateUserPassword(Authentication authentication, String nowpass, String pass, String repass);
}
