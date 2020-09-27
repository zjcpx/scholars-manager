package com.scholars.service;

import java.util.List;

import com.scholars.pojo.AnnoType;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/**
 * 
	* ClassName: IAnnoTypeService <br/> 
	* Function: TODO 公告类型管理接口. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-8-21 13:24:21 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */



public interface IAnnoTypeService {
	
	List<AnnoType> getAnnoTypeList();
	EUDataGridResult getAnnoTypeGrid(Integer page,Integer rows,String sort,String order,AnnoType annoType);
	TaotaoResult CreateAnnoType(AnnoType annoType);
	TaotaoResult updataAnnoType(AnnoType annoType);
	TaotaoResult deleteAnnoType(Long id);
	Boolean isSameAnnoTypeName(String annoTypeName);
	
	

}
