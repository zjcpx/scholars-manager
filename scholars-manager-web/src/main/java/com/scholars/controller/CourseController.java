package com.scholars.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scholars.pojo.Course;
import com.scholars.service.ICourseService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/**
 * 
	* ClassName: CourseController <br/> 
	* Function: TODO ADD FUNCTION. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-27 11:32:11 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */

@Controller
@RequestMapping("/Course")
public class CourseController {

	@Autowired
	private ICourseService courseServie;
	
	@InitBinder
	public void dateHandler(WebDataBinder wdb){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    wdb.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	public EUDataGridResult getCourseGrid(Integer page,Integer rows,String sort,String order,Course course) {
		EUDataGridResult result = courseServie.getCourseGrid(page, rows, sort, order, course);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult CreateCourse(Course course) {
		TaotaoResult result = courseServie.createCourse(course);
		return result;
	}
	
	@RequestMapping("updata")
	@ResponseBody
	public TaotaoResult updataCourse(Course course) {
		TaotaoResult result = courseServie.UpdataCourse(course);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteCourse(String ids) {
		String[] strings = ids.split(",");
		for (int i = 0; i < strings.length; i++) {
			Long id = Long.valueOf(strings[i]);
			TaotaoResult result = courseServie.DeleteCourse(id);
			if(result.getStatus() != 200) {
				return TaotaoResult.build(500, "删除课程失败");
			}
		}
		return TaotaoResult.ok();
	}
}
