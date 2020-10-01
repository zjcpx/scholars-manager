package com.scholars.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.PositionMapper;
import com.scholars.pojo.Position;
import com.scholars.pojo.PositionExample;
import com.scholars.pojo.PositionExample.Criteria;
import com.scholars.service.IPositionService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;
/**
 * 
	* ClassName: PositionServiceImpl <br/> 
	* Function: 职位管理Service实现类. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:24:23 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Service
public class PositionServiceImpl implements IPositionService {

	@Autowired
	private PositionMapper positiomMapper;
	@Override
	public List<Position> getPositionList() {
		PositionExample example = new PositionExample();
		List<Position> list = positiomMapper.selectByExample(example);
		return list;
	}
	@Override
	public EUDataGridResult getPositionGrid(Integer page, Integer rows, String sort, String order, Position position) {
		EUDataGridResult result = new EUDataGridResult();
		PositionExample example = new PositionExample();
		Criteria criteria = example.createCriteria();
		if (position != null) {
			Date createtime = position.getCreatetime();
			if(createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			Long id = position.getId();
			if (id != null) {
				criteria.andIdEqualTo(id);
			}
			Date modifytime = position.getModifytime();
			if(modifytime != null) {
				criteria.andModifytimeGreaterThanOrEqualTo(modifytime);
			}
			String positionName = position.getPositionname();
			if(StringUtils.isNotBlank(positionName)) {
				criteria.andPositionnameLike("%"+positionName+"%");
			}
		}
		example.setOrderByClause(sort+" "+order);
		
		PageHelper.startPage(page, rows);
		List<Position> list = positiomMapper.selectByExample(example);
		PageInfo<Position> info = new PageInfo<Position>(list);
		
		result.setRows(list);
		result.setTotal(info.getTotal());
		
		return result;
	}
	@Override
	public TaotaoResult CreatePosition(Position position) {
		position.setId(IDUtils.genItemId());
		position.setCreatetime(new Date());
		position.setModifytime(new Date());
		positiomMapper.insert(position);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult updataPosition(Position position) {
		Long id = position.getId();
		Position position2 = positiomMapper.selectByPrimaryKey(id);
		position.setCreatetime(position2.getCreatetime());
		position.setModifytime(new Date());
		positiomMapper.updateByPrimaryKey(position);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deletePosition(Long id) {
		positiomMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}
	@Override
	public Boolean isSamePosiName(String posiName) {
		PositionExample example = new PositionExample();
		Criteria criteria = example.createCriteria();
		criteria.andPositionnameEqualTo(posiName);
		List<Position> list = positiomMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return false;
		}
		return true;
	}
	@Override
	public String originPosi(Long id) {
		Position position = positiomMapper.selectByPrimaryKey(id);
		return position.getPositionname();
	}

}
