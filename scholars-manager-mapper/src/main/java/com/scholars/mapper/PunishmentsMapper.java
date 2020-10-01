package com.scholars.mapper;

import com.scholars.pojo.Punishments;
import com.scholars.pojo.PunishmentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PunishmentsMapper {
    int countByExample(PunishmentsExample example);

    int deleteByExample(PunishmentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Punishments record);

    int insertSelective(Punishments record);

    List<Punishments> selectByExampleWithBLOBs(PunishmentsExample example);

    List<Punishments> selectByExample(PunishmentsExample example);

    Punishments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Punishments record, @Param("example") PunishmentsExample example);

    int updateByExampleWithBLOBs(@Param("record") Punishments record, @Param("example") PunishmentsExample example);

    int updateByExample(@Param("record") Punishments record, @Param("example") PunishmentsExample example);

    int updateByPrimaryKeySelective(Punishments record);

    int updateByPrimaryKeyWithBLOBs(Punishments record);

    int updateByPrimaryKey(Punishments record);
}