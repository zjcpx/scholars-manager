package com.scholars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
 
/** 
	* ClassName: PageController <br/> 
	* Function: TODO Spring页面Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-8-21 11:26:39 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
	*/
@Controller
public class PageController {
 
	/** 
	 	*  	显示首页. <br/> 
		* TODO(这里描述这个方法适用条件 – 可选).<br/> 
		* TODO(这里描述这个方法的执行流程 – 可选).<br/> 
		* TODO(这里描述这个方法的使用方法 – 可选).<br/> 
		* TODO(这里描述这个方法的注意事项 – 可选).<br/> 
		* date: 2020-8-21 11:26:13.<br/>
		* @author Mike.Cai 
		* @return 
		* @since JDK 1.8
	*/
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}
}
