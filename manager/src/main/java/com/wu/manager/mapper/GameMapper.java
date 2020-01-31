package com.wu.manager.mapper;

import com.wu.manager.pojo.Game;
import com.wu.manager.pojo.GameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameMapper {
    long countByExample(GameExample example);

    int deleteByExample(GameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Game record);

    int insertSelective(Game record);

    List<Game> selectByExample(GameExample example);

    Game selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Game record, @Param("example") GameExample example);

    int updateByExample(@Param("record") Game record, @Param("example") GameExample example);

    int updateByPrimaryKeySelective(Game record);

    int updateByPrimaryKey(Game record);
}