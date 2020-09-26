package com.scholars.mapper;

import com.scholars.pojo.AnnoQa;
import com.scholars.pojo.AnnoQaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnoQaMapper {
    int countByExample(AnnoQaExample example);

    int deleteByExample(AnnoQaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnnoQa record);

    int insertSelective(AnnoQa record);

    List<AnnoQa> selectByExample(AnnoQaExample example);

    AnnoQa selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnnoQa record, @Param("example") AnnoQaExample example);

    int updateByExample(@Param("record") AnnoQa record, @Param("example") AnnoQaExample example);

    int updateByPrimaryKeySelective(AnnoQa record);

    int updateByPrimaryKey(AnnoQa record);
}