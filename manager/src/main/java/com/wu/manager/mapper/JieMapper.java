package com.wu.manager.mapper;

import com.wu.manager.pojo.Jie;
import com.wu.manager.pojo.JieExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JieMapper {
    long countByExample(JieExample example);

    int deleteByExample(JieExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jie record);

    int insertSelective(Jie record);

    List<Jie> selectByExampleWithBLOBs(JieExample example);

    List<Jie> selectByExample(JieExample example);

    Jie selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jie record, @Param("example") JieExample example);

    int updateByExampleWithBLOBs(@Param("record") Jie record, @Param("example") JieExample example);

    int updateByExample(@Param("record") Jie record, @Param("example") JieExample example);

    int updateByPrimaryKeySelective(Jie record);

    int updateByPrimaryKeyWithBLOBs(Jie record);

    int updateByPrimaryKey(Jie record);
}