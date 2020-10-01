package com.scholars.service;

import java.util.List;

import com.scholars.pojo.Role;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

public interface IRoleService {

	List<Role> getRoleList();
	EUDataGridResult getRoleDatagrid(Integer page,Integer rows,String sort,String order,Role role);
	TaotaoResult CreateRole(Role role);
	TaotaoResult UpdateRole(Role role);
	TaotaoResult DeleteRole(Long id);
	boolean isSameName(String roleName);
	String originRoleName(Long id);
}
