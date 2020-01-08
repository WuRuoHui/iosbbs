package com.wu.manager.mapper;

import com.wu.manager.pojo.FriendlyLink;
import com.wu.manager.pojo.FriendlyLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendlyLinkMapper {
    long countByExample(FriendlyLinkExample example);

    int deleteByExample(FriendlyLinkExample example);

    int deleteByPrimaryKey(Integer linkId);

    int insert(FriendlyLink record);

    int insertSelective(FriendlyLink record);

    List<FriendlyLink> selectByExample(FriendlyLinkExample example);

    FriendlyLink selectByPrimaryKey(Integer linkId);

    int updateByExampleSelective(@Param("record") FriendlyLink record, @Param("example") FriendlyLinkExample example);

    int updateByExample(@Param("record") FriendlyLink record, @Param("example") FriendlyLinkExample example);

    int updateByPrimaryKeySelective(FriendlyLink record);

    int updateByPrimaryKey(FriendlyLink record);
}