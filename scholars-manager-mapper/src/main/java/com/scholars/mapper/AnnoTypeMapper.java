package com.scholars.mapper;

import com.scholars.pojo.AnnoType;
import com.scholars.pojo.AnnoTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnoTypeMapper {
    int countByExample(AnnoTypeExample example);

    int deleteByExample(AnnoTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnnoType record);

    int insertSelective(AnnoType record);

    List<AnnoType> selectByExample(AnnoTypeExample example);

    AnnoType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnnoType record, @Param("example") AnnoTypeExample example);

    int updateByExample(@Param("record") AnnoType record, @Param("example") AnnoTypeExample example);

    int updateByPrimaryKeySelective(AnnoType record);

    int updateByPrimaryKey(AnnoType record);
}