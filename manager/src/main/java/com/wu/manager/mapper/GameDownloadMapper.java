package com.wu.manager.mapper;

import com.wu.manager.pojo.GameDownload;
import com.wu.manager.pojo.GameDownloadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameDownloadMapper {
    long countByExample(GameDownloadExample example);

    int deleteByExample(GameDownloadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameDownload record);

    int insertSelective(GameDownload record);

    List<GameDownload> selectByExample(GameDownloadExample example);

    GameDownload selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameDownload record, @Param("example") GameDownloadExample example);

    int updateByExample(@Param("record") GameDownload record, @Param("example") GameDownloadExample example);

    int updateByPrimaryKeySelective(GameDownload record);

    int updateByPrimaryKey(GameDownload record);
}