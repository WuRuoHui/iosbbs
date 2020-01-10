package com.wu.manager.mapper;

import com.wu.manager.pojo.SystemParameter;
import com.wu.manager.pojo.SystemParameterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemParameterMapper {
    long countByExample(SystemParameterExample example);

    int deleteByExample(SystemParameterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemParameter record);

    int insertSelective(SystemParameter record);

    List<SystemParameter> selectByExample(SystemParameterExample example);

    SystemParameter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemParameter record, @Param("example") SystemParameterExample example);

    int updateByExample(@Param("record") SystemParameter record, @Param("example") SystemParameterExample example);

    int updateByPrimaryKeySelective(SystemParameter record);

    int updateByPrimaryKey(SystemParameter record);
}