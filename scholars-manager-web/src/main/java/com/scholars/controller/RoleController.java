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

import com.scholars.pojo.Role;
import com.scholars.service.IEmployeeService;
import com.scholars.service.IRoleService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/**
 * 
	* ClassName: RoleController <br/> 
	* Function: 角色管理Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:19:38 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Controller
@RequestMapping("/Role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IEmployeeService empService;
	
	@InitBinder
	public void dateHandler(WebDataBinder wdb){
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    wdb.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Role> getAllRoleList(){
	List<Role> list = roleService.getRoleList();
		return list;
	}
	
	@RequestMapping("/datagrid")
	@ResponseBody
	public EUDataGridResult getRoleGrid(Integer page,Integer rows,String sort,String order,Role role) {
		EUDataGridResult result = roleService.getRoleDatagrid(page, rows, sort, order, role);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult CreateRole(Role role) {
		String roleName = role.getRolename();
		if(roleService.isSameName(roleName)) {
			TaotaoResult result = roleService.CreateRole(role);
			return result;
		}
		return TaotaoResult.build(500, "角色名已存在，请更正");
	}
	
	@RequestMapping("/updata")
	@ResponseBody
	public TaotaoResult updateRole(Role role) {
		String name = role.getRolename();
		Long id = role.getId();
		if(roleService.isSameName(name)) {
			String originRoleName = roleService.originRoleName(id);
			TaotaoResult result = roleService.UpdateRole(role);
			
			empService.changeInfo(name, originRoleName, null, null, null, null);
			return result;
		}else {
			return TaotaoResult.build(500, "角色名已存在，请更正");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteRole(String ids) {
		String[] id = ids.split(",");
		for (String string : id) {
			Long roleId = Long.valueOf(string);
			TaotaoResult result = roleService.DeleteRole(roleId);
			if (result.getStatus() != 200) {
				return TaotaoResult.build(500, "删除角色失败");
			}
		}
		return TaotaoResult.ok();
	}
}
