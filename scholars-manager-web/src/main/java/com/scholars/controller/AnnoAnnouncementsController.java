package com.scholars.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.scholars.pojo.AnnoAnnouncements;
import com.scholars.service.IAnnosService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/** 
	* ClassName: AnnoAnnouncementsController <br/> 
	* Function: TODO 公告管理Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-8-21 12:06:49 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
	*/

@Controller
@RequestMapping("/anno")
public class AnnoAnnouncementsController {

	@Autowired
	private IAnnosService annosService;
	
	//将字符串转换为Date类
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        
    }
    
	/**
	 * 
		 * 获取全部公告清单. <br/> 
		* TODO(这里描述这个方法适用条件 – 可选).<br/> 
		* TODO(这里描述这个方法的执行流程 – 可选).<br/> 
		* TODO(这里描述这个方法的使用方法 – 可选).<br/> 
		* TODO(这里描述这个方法的注意事项 – 可选).<br/> 
		* date: 2020-8-21 16:02:32.<br/>
		* @author Mike.Cai 
		* @param page：分页数
		* @param rows：每页显示的记录数
		* @return 
		* @since JDK 1.8
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getAnnoList(Integer page, Integer rows) {
		EUDataGridResult result = annosService.getAnnosResultList(page, rows);
		return result;
	}
	
	/**
	 * 
		 * 增加一条公告记录. <br/> 
		* TODO(这里描述这个方法适用条件 – 可选).<br/> 
		* TODO(这里描述这个方法的执行流程 – 可选).<br/> 
		* TODO(这里描述这个方法的使用方法 – 可选).<br/> 
		* TODO(这里描述这个方法的注意事项 – 可选).<br/> 
		* date: 2020-8-21 16:04:15.<br/>
		* @author Mike.Cai 
		* @param anno
		* @return 
		* @since JDK 1.8
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createAnno(AnnoAnnouncements anno) {
		TaotaoResult result = annosService.createAnnoAnnouncements(anno);
		return result;
	}
	
	/**
	 * 
		 *删除公告. <br/> 
		* TODO(这里描述这个方法适用条件 – 可选).<br/> 
		* TODO(这里描述这个方法的执行流程 – 可选).<br/> 
		* TODO(这里描述这个方法的使用方法 – 可选).<br/> 
		* TODO(这里描述这个方法的注意事项 – 可选).<br/> 
		* date: 2020-8-21 16:04:57.<br/>
		* @author Mike.Cai 
		* @param id：公告ID列表
		* @return 
		* @since JDK 1.8
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteAnno(String id) {
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			Long tempId = Long.parseLong(ids[i]);
			TaotaoResult result = annosService.deleteAnnoAnnouncements(tempId);
			if (result.getStatus() != 200) {
				return TaotaoResult.build(400, "删除公告失败");
			}
		}
		return TaotaoResult.ok();
		
	}
	
	/**
	 * 
		 * 修改公告记录. <br/> 
		* TODO(这里描述这个方法适用条件 – 可选).<br/> 
		* TODO(这里描述这个方法的执行流程 – 可选).<br/> 
		* TODO(这里描述这个方法的使用方法 – 可选).<br/> 
		* TODO(这里描述这个方法的注意事项 – 可选).<br/> 
		* date: 2020-8-21 16:06:08.<br/>
		* @author Mike.Cai 
		* @param anno
		* @return 
		* @since JDK 1.8
	 */
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updataAnno(AnnoAnnouncements anno) {
		annosService.upDataAnno(anno);
		return TaotaoResult.ok();
	}
	
	@RequestMapping( value = "/annoList2",method = RequestMethod.POST)
	@ResponseBody
	public EUDataGridResult getAnnoList(Integer page, Integer rows, 
			String sort, String order,AnnoAnnouncements anno) {
		EUDataGridResult result = annosService.getAnnoListByParam(page, rows, sort, order,anno);
		return  result;
	}
	
}
