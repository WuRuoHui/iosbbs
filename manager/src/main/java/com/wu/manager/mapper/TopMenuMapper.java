package com.wu.manager.mapper;

import com.wu.manager.pojo.TopMenu;
import com.wu.manager.pojo.TopMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopMenuMapper {
    long countByExample(TopMenuExample example);

    int deleteByExample(TopMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TopMenu record);

    int insertSelective(TopMenu record);

    List<TopMenu> selectByExample(TopMenuExample example);

    TopMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TopMenu record, @Param("example") TopMenuExample example);

    int updateByExample(@Param("record") TopMenu record, @Param("example") TopMenuExample example);

    int updateByPrimaryKeySelective(TopMenu record);

    int updateByPrimaryKey(TopMenu record);
}