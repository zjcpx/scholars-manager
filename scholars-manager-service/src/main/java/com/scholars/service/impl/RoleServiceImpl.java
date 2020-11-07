package com.scholars.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.WeakReferenceMonitor.ReleaseListener;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.RoleMapper;
import com.scholars.pojo.Role;
import com.scholars.pojo.RoleExample;
import com.scholars.pojo.RoleExample.Criteria;
import com.scholars.service.IRoleService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;
/**
 * 
	* ClassName: RoleServiceImpl <br/> 
	* Function: TODO 角色管理Service实现类. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:16:24 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> getRoleList() {
		RoleExample example = new RoleExample();
		List<Role> list = roleMapper.selectByExample(example);
		return list;
	}

	@Override
	public EUDataGridResult getRoleDatagrid(Integer page, Integer rows, String sort, String order, Role role) {
		EUDataGridResult result = new EUDataGridResult();
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		if(role != null) {
			Date createtime = role.getCreatetime();
			if (createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			Long id = role.getId();
			if (id != null) {
				criteria.andIdEqualTo(id);
				
			}
			Date modifytime = role.getModifytime();
			if(modifytime != null) {
				criteria.andModifytimeGreaterThanOrEqualTo(modifytime);
			}
			String roleName = role.getRolename();
			if(StringUtils.isNotBlank(roleName)) {
				criteria.andRolenameLike("%"+roleName+"%");
			}
		}
		
		example.setOrderByClause(sort+" "+order);
		PageHelper.startPage(page, rows);
		List<Role> list = roleMapper.selectByExample(example);
		PageInfo<Role> info = new PageInfo<Role>(list);
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}

	@Override
	public TaotaoResult CreateRole(Role role) {
		role.setId(IDUtils.genItemId());
		role.setCreatetime(new Date());
		role.setModifytime(new Date());
		roleMapper.insert(role);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult UpdateRole(Role role) {
		Long id = role.getId();
		Role role2 = roleMapper.selectByPrimaryKey(id);
		role.setCreatetime(role2.getCreatetime());
		role.setModifytime(new Date());
		roleMapper.updateByPrimaryKeySelective(role);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult DeleteRole(Long id) {
		roleMapper.deleteByPrimaryKey(id);// TODO Auto-generated method stub
		return TaotaoResult.ok();
	}

	@Override
	public boolean isSameName(String roleName) {
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRolenameEqualTo(roleName);
		List<Role> list = roleMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

}
