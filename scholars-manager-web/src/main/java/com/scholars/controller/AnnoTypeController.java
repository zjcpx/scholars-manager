package com.scholars.controller;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scholars.pojo.AnnoType;
import com.scholars.service.IAnnoTypeService;

/**
 * 
	* ClassName: AnnoTypeController <br/> 
	* Function: 公告类型管理Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-8-21 13:55:14 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */

@Controller
@RequestMapping("/annotype")
public class AnnoTypeController {

	@Autowired
	private IAnnoTypeService annoTypeService;
	
	@InitBinder
	public void dateHandler(WebDataBinder wdb){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    wdb.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));
	}
	
	@RequestMapping("/annoTypeList")
	@ResponseBody
	public List<AnnoType> getAnnoTypeList(){
		List<AnnoType> list = annoTypeService.getAnnoTypeList();
		return list;
	}
	
	
}
