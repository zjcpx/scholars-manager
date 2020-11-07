package com.scholars.service;

import java.util.List;

import com.scholars.pojo.AnnoType;

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

}
