package com.scholars.service;

import java.util.List;

import com.scholars.pojo.Department;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

public interface IDepartmentService {

	List<Department> getDepList();
	EUDataGridResult getDepartmentGrid(Integer page,Integer rows,String sort,String order,Department dep);
	TaotaoResult CreateDepartment(Department dep);
	TaotaoResult DeleteDepartment(Long id);
	TaotaoResult updataDepartment(Department dep);
	Boolean isSameDepName(String depName);
	String originDep(Long id);
	
}
