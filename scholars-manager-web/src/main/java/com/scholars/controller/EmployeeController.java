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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.scholars.pojo.Employee;
import com.scholars.service.IEmployeeService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/**
 * 
	* ClassName: EmployeeController <br/> 
	* Function: TODO 员工管理Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:45:15 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */

@Controller
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	//将字符串转换为Date类
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        
    }
	
	@RequestMapping("/Datagrid")
	@ResponseBody
	public EUDataGridResult getEmployeeGrid(Integer page,Integer rows,String sort,String order,Employee employee) {
		EUDataGridResult result = employeeService.getEmployeeDataGrid(page, rows, sort, order, employee);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult createEeployee(Employee employee) {
		String no = employee.getNo();
		if(employeeService.isSameNo(no)) {
			return employeeService.createEmployee(employee);
		}else {
			return TaotaoResult.build(500, "工号已存在");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteEmployee(String ids) {
		String[] id = ids.split(",");
		int idLength = id.length;
		int num = 0;
		for (String string : id) {
			
			Long long1 = Long.valueOf(string);
			TaotaoResult result = employeeService.deleteEmployee(long1);
			if (result.getStatus() == 200) {
				num++;
				continue;
			}else {
				break;
			}
		}
		if(num < idLength) {
			return TaotaoResult.build(500, "删除员工记录失败");
		}else {
			return TaotaoResult.ok();
		}
	}
	
	@RequestMapping("/updata")
	@ResponseBody
	public TaotaoResult updataEmployee(Employee employee) {
		TaotaoResult result = employeeService.updataEmployee(employee);
		return result;
	}
}
