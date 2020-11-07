package com.scholars.controller;

import java.text.DateFormat;
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
import org.springframework.web.context.request.WebRequest;

import com.scholars.pojo.Department;
import com.scholars.service.IDepartmentService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/**
 * 
	* ClassName: DepartmentController <br/> 
	* Function: TODO 部门管理Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:36:20 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Controller
@RequestMapping("/Deportment")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;
	
	//将字符串转换为Date类
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        
    }
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Department> getAllDepartment(){
		List<Department> list = departmentService.getDepList();
		return list;
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	public EUDataGridResult getDepDataGridResult(Integer page,Integer rows,String sort,String order,Department dep) {
		EUDataGridResult grid = departmentService.getDepartmentGrid(page, rows, sort, order, dep);
		return grid;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult CreateDepmpartment(Department dep) {
		String depname = dep.getDepname();
		if(departmentService.isSameDepName(depname)) {
			TaotaoResult result = departmentService.CreateDepartment(dep);
			return result;
		}
		return TaotaoResult.build(500, "部门名称重复");
	}
	
	@RequestMapping("/updata")
	@ResponseBody
	public TaotaoResult UpdataDepartment(Department dep) {
		String depname = dep.getDepname();
		if(departmentService.isSameDepName(depname)) {
			departmentService.updataDepartment(dep);
			return TaotaoResult.ok();
		}
		return TaotaoResult.build(500, "部门名称重复");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult DeleteDepartment(String ids) {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			Long long1 = Long.valueOf(id[i]);
			TaotaoResult result = departmentService.DeleteDepartment(long1);
			if (result.getStatus() != 200) {
				return TaotaoResult.build(500, "删除部门失败！");
			}
		}
		return TaotaoResult.ok();
	}
	
}
