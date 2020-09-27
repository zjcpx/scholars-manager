package com.scholars.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.AnnoTypeMapper;
import com.scholars.pojo.AnnoAnnouncementsExample;
import com.scholars.pojo.AnnoType;
import com.scholars.pojo.AnnoTypeExample;
import com.scholars.pojo.AnnoTypeExample.Criteria;
import com.scholars.service.IAnnoTypeService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;

/**
 * 
	* ClassName: AnnoTypeServiceImpl <br/> 
	* Function: 公告类型管理Service实现类. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-8-21 13:46:43 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Service
public class AnnoTypeServiceImpl implements IAnnoTypeService {

	@Autowired
	private AnnoTypeMapper annoTypeMapper;
	
	@Override
	public List<AnnoType> getAnnoTypeList() {
		//设置公告列表的查询条件
		AnnoTypeExample example = new AnnoTypeExample();
		//查询所有公告类型
		List<AnnoType> list = annoTypeMapper.selectByExample(example);
		//返回查询结果
		return list;
	}

	@Override
	public EUDataGridResult getAnnoTypeGrid(Integer page, Integer rows, String sort, String order, AnnoType annoType) {
		EUDataGridResult result = new EUDataGridResult();
		AnnoTypeExample example = new AnnoTypeExample();
		Criteria criteria = example.createCriteria();
		
		if(annoType != null) {
			Date createtime = annoType.getCreatetime();
			if (createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			Long id = annoType.getId();
			if(id != null) {
				criteria.andIdEqualTo(id);
			}
			Date modifytime = annoType.getModifytime();
			if (modifytime != null) {
				criteria.andModifytimeGreaterThanOrEqualTo(modifytime);
			}
			String typename = annoType.getTypename();
			if (StringUtils.isNotBlank(typename)) {
				criteria.andTypenameLike("%"+typename+"%");
			}
		}
		example.setOrderByClause(sort+" "+order);
		PageHelper.startPage(page, rows);
		List<AnnoType> list = annoTypeMapper.selectByExample(example);
		PageInfo<AnnoType> info = new PageInfo<AnnoType>(list);
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}

	@Override
	public TaotaoResult CreateAnnoType(AnnoType annoType) {
		annoType.setId(IDUtils.genItemId());
		annoType.setCreatetime(new Date());
		annoType.setModifytime(new Date());
		annoTypeMapper.insert(annoType);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updataAnnoType(AnnoType annoType) {
		Long id = annoType.getId();

		AnnoType annoType2 = annoTypeMapper.selectByPrimaryKey(id);

		annoType.setCreatetime(annoType2.getCreatetime());
		
		annoTypeMapper.updateByPrimaryKey(annoType);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteAnnoType(Long id) {
		annoTypeMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

	@Override
	public Boolean isSameAnnoTypeName(String annoTypeName) {
		AnnoTypeExample example = new AnnoTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypenameEqualTo(annoTypeName);
		List<AnnoType> list = annoTypeMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

}
