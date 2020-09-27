package com.scholars.mapper;

import com.scholars.pojo.AnnoReadrec;
import com.scholars.pojo.AnnoReadrecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnoReadrecMapper {
    int countByExample(AnnoReadrecExample example);

    int deleteByExample(AnnoReadrecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnnoReadrec record);

    int insertSelective(AnnoReadrec record);

    List<AnnoReadrec> selectByExample(AnnoReadrecExample example);

    AnnoReadrec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnnoReadrec record, @Param("example") AnnoReadrecExample example);

    int updateByExample(@Param("record") AnnoReadrec record, @Param("example") AnnoReadrecExample example);

    int updateByPrimaryKeySelective(AnnoReadrec record);

    int updateByPrimaryKey(AnnoReadrec record);
}