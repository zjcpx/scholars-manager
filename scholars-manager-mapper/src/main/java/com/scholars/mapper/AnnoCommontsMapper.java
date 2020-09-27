package com.scholars.mapper;

import com.scholars.pojo.AnnoCommonts;
import com.scholars.pojo.AnnoCommontsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnoCommontsMapper {
    int countByExample(AnnoCommontsExample example);

    int deleteByExample(AnnoCommontsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnnoCommonts record);

    int insertSelective(AnnoCommonts record);

    List<AnnoCommonts> selectByExample(AnnoCommontsExample example);

    AnnoCommonts selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnnoCommonts record, @Param("example") AnnoCommontsExample example);

    int updateByExample(@Param("record") AnnoCommonts record, @Param("example") AnnoCommontsExample example);

    int updateByPrimaryKeySelective(AnnoCommonts record);

    int updateByPrimaryKey(AnnoCommonts record);
}