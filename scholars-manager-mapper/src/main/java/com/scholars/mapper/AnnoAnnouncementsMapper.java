package com.scholars.mapper;

import com.scholars.pojo.AnnoAnnouncements;
import com.scholars.pojo.AnnoAnnouncementsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnoAnnouncementsMapper {
    int countByExample(AnnoAnnouncementsExample example);

    int deleteByExample(AnnoAnnouncementsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnnoAnnouncements record);

    int insertSelective(AnnoAnnouncements record);

    List<AnnoAnnouncements> selectByExampleWithBLOBs(AnnoAnnouncementsExample example);

    List<AnnoAnnouncements> selectByExample(AnnoAnnouncementsExample example);

    AnnoAnnouncements selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnnoAnnouncements record, @Param("example") AnnoAnnouncementsExample example);

    int updateByExampleWithBLOBs(@Param("record") AnnoAnnouncements record, @Param("example") AnnoAnnouncementsExample example);

    int updateByExample(@Param("record") AnnoAnnouncements record, @Param("example") AnnoAnnouncementsExample example);

    int updateByPrimaryKeySelective(AnnoAnnouncements record);

    int updateByPrimaryKeyWithBLOBs(AnnoAnnouncements record);

    int updateByPrimaryKey(AnnoAnnouncements record);
}