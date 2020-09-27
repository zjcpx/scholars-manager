package com.scholars.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.DepartmentMapper;
import com.scholars.pojo.Department;
import com.scholars.pojo.DepartmentExample;
import com.scholars.pojo.DepartmentExample.Criteria;
import com.scholars.service.IDepartmentService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;
/**
 * 
	* ClassName: DepartmentServiceImpl <br/> 
	* Function: TODO 部门管理Service实现类. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:32:47 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMappet;
	
	@Override
	public List<Department> getDepList() {
		DepartmentExample example = new DepartmentExample();
		List<Department> list = departmentMappet.selectByExample(example);
		return list;
	}

	@Override
	public EUDataGridResult getDepartmentGrid(Integer page, Integer rows, String sort, String order, Department dep) {
		EUDataGridResult result = new EUDataGridResult();
		DepartmentExample example = new DepartmentExample();
		Criteria criteria = example.createCriteria();
		if (dep != null) {
			Date createtime = dep.getCreatetime();
			if(createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			String depmanager = dep.getDepmanager();
			if (StringUtils.isNotBlank(depmanager)) {
				criteria.andDepmanagerLike("%"+depmanager+"%");
			}
			String depname = dep.getDepname();
			if(StringUtils.isNotBlank(depname)) {
				criteria.andDepnameLike("%"+depname+"%");
			}
			Long id = dep.getId();
			if(id != null){
				criteria.andIdEqualTo(id);
			}
			Date modifytime = dep.getModifytime();
			if (modifytime != null) {
				criteria.andModifytimeGreaterThanOrEqualTo(modifytime);
			}
			example.setOrderByClause(sort+" "+order);
			PageHelper.startPage(page, rows);
			List<Department> list = departmentMappet.selectByExample(example);
			PageInfo<Department> info = new PageInfo<Department>(list);
			result.setRows(list);
			result.setTotal(info.getTotal());
		}
		return result;
	}

	@Override
	public TaotaoResult CreateDepartment(Department dep) {
		dep.setId(IDUtils.genItemId());
		dep.setCreatetime(new Date());
		dep.setModifytime(new Date());
		departmentMappet.insert(dep);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult DeleteDepartment(Long ids) {
		departmentMappet.deleteByPrimaryKey(ids);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updataDepartment(Department dep) {
		Long id = dep.getId();
		Department department = departmentMappet.selectByPrimaryKey(id);
		dep.setCreatetime(department.getCreatetime());
		dep.setModifytime(new Date());
		departmentMappet.updateByPrimaryKey(dep);
		return TaotaoResult.ok();
	}

	@Override
	public Boolean isSameDepName(String depName) {
		DepartmentExample example = new DepartmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andDepnameEqualTo(depName);
		List<Department> list = departmentMappet.selectByExample(example);
		if(list != null && list.size() >0) {
			return false;
		}
		return true;
	}

}
