package com.wu.manager.mapper;

import com.wu.manager.pojo.GameContact;
import com.wu.manager.pojo.GameContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameContactMapper {
    long countByExample(GameContactExample example);

    int deleteByExample(GameContactExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameContact record);

    int insertSelective(GameContact record);

    List<GameContact> selectByExample(GameContactExample example);

    GameContact selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameContact record, @Param("example") GameContactExample example);

    int updateByExample(@Param("record") GameContact record, @Param("example") GameContactExample example);

    int updateByPrimaryKeySelective(GameContact record);

    int updateByPrimaryKey(GameContact record);
}