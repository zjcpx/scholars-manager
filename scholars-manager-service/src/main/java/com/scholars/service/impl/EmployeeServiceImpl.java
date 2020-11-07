package com.scholars.service.impl;

import java.util.Date;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.EmployeeMapper;
import com.scholars.pojo.Employee;
import com.scholars.pojo.EmployeeExample;
import com.scholars.pojo.EmployeeExample.Criteria;
import com.scholars.service.IEmployeeService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;
/**
 * 
	* ClassName: EmployeeServiceImpl <br/> 
	* Function: TODO 员工管理Service实现类. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:40:07 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	/**
	 * 
		* TODO 简单描述该方法的实现功能（可选）. 
		* @see com.scholars.service.IEmployeeService#getEmployeeDataGrid(int, int, java.lang.String, java.lang.String, com.scholars.pojo.Employee)
	 */
	@Override
	public EUDataGridResult getEmployeeDataGrid(int page, int rows, String sort, String order, Employee employee) {
		EUDataGridResult result = new EUDataGridResult();
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//判断是否有检索条件
		if (employee != null) {
			Date createtime = employee.getCreatetime();
			if (createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			String dep = employee.getDep();
			if (StringUtils.isNotBlank(dep)) {
				criteria.andDepLike("%"+dep+"%");
			}
			String email = employee.getEmail();
			if(StringUtils.isNotBlank(email)) {
				criteria.andEmailLike("%"+email+"%");
			}
			Long id = employee.getId();
			if (id != null) {
				criteria.andIdEqualTo(id);
			}
			Date modifytime = employee.getModifytime();
			if (modifytime != null) {
				criteria.andModifytimeGreaterThanOrEqualTo(modifytime);
			}
			String name = employee.getName();
			if (StringUtils.isNotBlank(name)) {
				criteria.andNameLike("%"+name+"%");
			}
			String nickname = employee.getNickname();
			if (StringUtils.isNotBlank(nickname)) {
				criteria.andNicknameLike("%"+nickname+"%");
			}
			String no = employee.getNo();
			if (StringUtils.isNotBlank(no)) {
				criteria.andNoEqualTo(no);
			}
			String position = employee.getPosition();
			if (StringUtils.isNotBlank(position)) {
				criteria.andPositionLike("%"+position+"%");
			}
			String qq = employee.getQq();
			if (StringUtils.isNotBlank(qq)) {
				criteria.andQqLike("%"+qq+"%");
			}
			String role = employee.getRole();
			if (StringUtils.isNotBlank(role)) {
				criteria.andRoleLike("%"+role+"%");
			}
			Integer score = employee.getScore();
			if (score != null) {
				criteria.andScoreEqualTo(score);
			}
			String tel = employee.getTel();
			if (StringUtils.isNotBlank(tel)) {
				criteria.andTelLike("%"+tel+"%");
			}
			String wechat = employee.getWechat();
			if (StringUtils.isNotBlank(wechat)) {
				criteria.andWechatLike("%"+wechat+"%");
			}
		}
		//设置排序
		example.setOrderByClause(sort+" "+order);
		
		PageHelper.startPage(page, rows);
		List<Employee> list = employeeMapper.selectByExample(example);
		PageInfo<Employee> info = new PageInfo<Employee>(list);
		result.setRows(list);
		result.setTotal(info.getTotal());
		
		return result;
	}
	/**
	 * 创建员工
	 */
	@Override
	public TaotaoResult createEmployee(Employee employee) {
		employee.setId(IDUtils.genItemId());
		String password = employee.getPassword();
		if (password == null) {
			password = DigestUtils.md5DigestAsHex("123".getBytes());
		}
		employee.setPassword(password);
		employee.setCreatetime(new Date());
		employee.setModifytime(new Date());
		employeeMapper.insert(employee);
		
		return TaotaoResult.ok();
	}
	@Override
	public Boolean isSameNo(String no) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNoEqualTo(no);
		List<Employee> list = employeeMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return false;
		}
		return true;
	}
	@Override
	public TaotaoResult updataEmployee(Employee employee) {
		Long id = employee.getId();
		Employee employee2 = employeeMapper.selectByPrimaryKey(id);
		employee.setCreatetime(employee2.getCreatetime());
		employeeMapper.updateByPrimaryKey(employee);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteEmployee(Long id) {
		employeeMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

}
