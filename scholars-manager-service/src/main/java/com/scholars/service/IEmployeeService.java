package com.scholars.service;

import java.util.List;

import com.scholars.pojo.Employee;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

public interface IEmployeeService {

	EUDataGridResult getEmployeeDataGrid(int page,int rows,String sort,String order,Employee  employee);
	TaotaoResult createEmployee(Employee employee);
	Boolean isSameNo(String no);
	TaotaoResult updataEmployee(Employee employee);
	TaotaoResult deleteEmployee(Long id);
	List<Employee> getEmployeesByDepartment(String depName);
	String getUserNameByNo(String name);
	List<Employee> getEmployeesByRole(String roleNam);
	TaotaoResult changeInfo(String newRoleName, String orginRoleName,String newDep, String orginDep,String newPosi, String orginPsi);
	TaotaoResult empNamebyNo(String no);
}
