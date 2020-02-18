package com.wu.manager.mapper;

import com.wu.manager.pojo.Passageway;
import com.wu.manager.pojo.PassagewayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PassagewayMapper {
    long countByExample(PassagewayExample example);

    int deleteByExample(PassagewayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Passageway record);

    int insertSelective(Passageway record);

    List<Passageway> selectByExample(PassagewayExample example);

    Passageway selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Passageway record, @Param("example") PassagewayExample example);

    int updateByExample(@Param("record") Passageway record, @Param("example") PassagewayExample example);

    int updateByPrimaryKeySelective(Passageway record);

    int updateByPrimaryKey(Passageway record);
}