package com.scholars.service;

import java.util.List;

import com.scholars.pojo.AnnoAnnouncements;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/** 
	* ClassName: IAnnosService <br/> 
	* Function: 公告管理接口. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-8-21 11:39:15 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
	*/
public interface IAnnosService {
	
	//获取所有公告列表(分页)
	EUDataGridResult getAnnosResultList(int page,int rows);
	//增加公告
	TaotaoResult createAnnoAnnouncements(AnnoAnnouncements anno);
	//删除公告
	TaotaoResult deleteAnnoAnnouncements(long id);
	//更新公告
	TaotaoResult upDataAnno(AnnoAnnouncements anno);
	//根据EasyUi DataGrid参数查询公告列表
	EUDataGridResult getAnnoListByParam(int page,int rows,String sortName,String sortOrder,AnnoAnnouncements anno);
	//获取所有公告信息
	List<AnnoAnnouncements> getAllAnnoList();
}
