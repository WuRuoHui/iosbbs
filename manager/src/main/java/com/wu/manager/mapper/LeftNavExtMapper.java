package com.wu.manager.mapper;

import com.wu.manager.pojo.LeftNav;

import java.util.List;

public interface LeftNavExtMapper {
    List<LeftNav> selectLeftMenuByRoleId(Integer roleId,Integer parentId,Integer menuLevel);
}