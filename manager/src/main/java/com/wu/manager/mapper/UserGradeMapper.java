package com.wu.manager.mapper;

import com.wu.manager.pojo.UserGrade;
import com.wu.manager.pojo.UserGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGradeMapper {
    long countByExample(UserGradeExample example);

    int deleteByExample(UserGradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGrade record);

    int insertSelective(UserGrade record);

    List<UserGrade> selectByExample(UserGradeExample example);

    UserGrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGrade record, @Param("example") UserGradeExample example);

    int updateByExample(@Param("record") UserGrade record, @Param("example") UserGradeExample example);

    int updateByPrimaryKeySelective(UserGrade record);

    int updateByPrimaryKey(UserGrade record);
}