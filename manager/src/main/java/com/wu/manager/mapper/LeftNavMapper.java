package com.wu.manager.mapper;

import com.wu.manager.pojo.LeftNav;
import com.wu.manager.pojo.LeftNavExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeftNavMapper {
    long countByExample(LeftNavExample example);

    int deleteByExample(LeftNavExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeftNav record);

    int insertSelective(LeftNav record);

    List<LeftNav> selectByExample(LeftNavExample example);

    LeftNav selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeftNav record, @Param("example") LeftNavExample example);

    int updateByExample(@Param("record") LeftNav record, @Param("example") LeftNavExample example);

    int updateByPrimaryKeySelective(LeftNav record);

    int updateByPrimaryKey(LeftNav record);
}