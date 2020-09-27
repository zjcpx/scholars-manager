package com.scholars.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.PunishmentsMapper;
import com.scholars.pojo.Punishments;
import com.scholars.pojo.PunishmentsExample;
import com.scholars.pojo.PunishmentsExample.Criteria;
import com.scholars.service.IPunishmentsService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;
/**
 * 
	* ClassName: PunishmentsServiceImpl <br/> 
	* Function: TODO 处罚管理Service. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-24 11:36:12 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Service
public class PunishmentsServiceImpl implements IPunishmentsService {

	@Autowired
	private PunishmentsMapper punishmentMapper;
	@Override
	public EUDataGridResult getPunishmentsGrid(Integer page, Integer rows, String sort, String order,
			Punishments punishment) {
		EUDataGridResult result = new EUDataGridResult();
		PunishmentsExample example = new PunishmentsExample();
		Criteria criteria = example.createCriteria();
		if (punishment != null) {
			Date createtime = punishment.getCreatetime();
			if (createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			String describ = punishment.getDescrib();
			if (StringUtils.isNotBlank(describ)) {
				criteria.andDescribLike("%"+describ+"%");
			}
			Long long1 = punishment.getId();
			if(long1 != null) {
				criteria.andIdEqualTo(long1);
			}
			String person = punishment.getPerson();
			if (StringUtils.isNotBlank(person)) {
				criteria.andPersonEqualTo(person);
			}
			String resone = punishment.getResone();
			if (StringUtils.isNotBlank(resone)) {
				criteria.andResoneLike("%"+resone+"%");
			}
			Integer result2 = punishment.getResult();
			if(result2 != null) {
				criteria.andResultEqualTo(result2);
			}
			example.setOrderByClause(sort+" "+order);
			
			PageHelper.startPage(page, rows);
			
			List<Punishments> list = punishmentMapper.selectByExample(example);
			
			PageInfo<Punishments> info = new PageInfo<Punishments>(list);
			
			result.setRows(list);
			result.setTotal(info.getTotal());
		}
		return result;
	}

	@Override
	public TaotaoResult CreatePunishment(Punishments punishment) {
		punishment.setId(IDUtils.genItemId());
		punishment.setCreatetime(new Date());
		punishmentMapper.insert(punishment);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult UpdataPunishment(Punishments punishment) {
		
		return null;
	}

	@Override
	public TaotaoResult DeletePunishment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
