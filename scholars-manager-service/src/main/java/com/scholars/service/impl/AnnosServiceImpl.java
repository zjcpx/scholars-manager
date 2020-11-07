package com.scholars.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.AnnoAnnouncementsMapper;
import com.scholars.pojo.AnnoAnnouncements;
import com.scholars.pojo.AnnoAnnouncementsExample;
import com.scholars.pojo.AnnoAnnouncementsExample.Criteria;
import com.scholars.service.IAnnosService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;

/** 
	* ClassName: AnnosServiceImpl <br/> 
	* Function: TODO 公告管理service实现类. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-8-21 11:45:38 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
	*/
@Service
public class AnnosServiceImpl implements IAnnosService {

	@Autowired
	private AnnoAnnouncementsMapper annoMapper;
	/**
	 * 
		* TODO 以分页的方式返回公告列表. 
		* @see com.scholars.service.IAnnosService#getAnnosResultList(int, int)
	 */
	@Override
	public EUDataGridResult getAnnosResultList(int page, int rows) {
		//申明返回的结果变量
		EUDataGridResult result = new EUDataGridResult();
		//设置公共列表查询条件
		AnnoAnnouncementsExample example = new AnnoAnnouncementsExample();
		//设置分页参数
		PageHelper.startPage(page, rows);
		//执行查询
		List<AnnoAnnouncements> list = annoMapper.selectByExampleWithBLOBs(example);
		//获取公告的总数
		PageInfo<AnnoAnnouncements> info = new PageInfo<AnnoAnnouncements>(list);
		//给返回的结果赋值
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}
	
	/**
	 * 
		* TODO 补全公告的信息，调用服务，实现公告持久化
		* @see com.scholars.service.IAnnosService#createAnnoAnnouncements(com.scholars.pojo.AnnoAnnouncements)
	 */
	@Override
	public TaotaoResult createAnnoAnnouncements(AnnoAnnouncements anno) {
		//补全公告的创建时间
		anno.setCreatetime(new Date());
		//补全公告更新时间
		anno.setModifytime(new Date());
		//生成随机码，补全公告编号
		anno.setId(IDUtils.genItemId());
		//持久化公告信息
		annoMapper.insert(anno);
		//返回操作结果
		return TaotaoResult.ok();
		
	}
	
	/**
	 * 
		* TODO 根据公告ID，删除公共）. 
		* @see com.scholars.service.IAnnosService#deleteAnnoAnnouncements(long)
	 */
	@Override
	public TaotaoResult deleteAnnoAnnouncements(long id) {
		//根据ID删除公告
		annoMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

	/**
	 * 
		* TODO 更新指定的公告记录. 
		* @see com.scholars.service.IAnnosService#upDataAnno(com.scholars.pojo.AnnoAnnouncements)
	 */
	@Override
	public TaotaoResult upDataAnno(AnnoAnnouncements anno) {
		//获取被更新公告的ID
		Long id = anno.getId();
		//AnnoAnnouncementsExample example = new AnnoAnnouncementsExample();
		AnnoAnnouncements tempAnno = annoMapper.selectByPrimaryKey(id);
		anno.setCreatetime(tempAnno.getCreatetime());
		anno.setModifytime(new Date());
		annoMapper.updateByPrimaryKeyWithBLOBs(anno);
		return TaotaoResult.ok();
	}

	/**
	 * 
		* TODO 根据前台传递的分页和排序参数，查询公告列表. 
		* @see com.scholars.service.IAnnosService#getAnnoListByParam(int, int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public EUDataGridResult getAnnoListByParam(int page, int rows, String sortName, String sortOrder,AnnoAnnouncements anno) {
		
		//申明返回变量
		EUDataGridResult result = new EUDataGridResult();
		//创建查询条件
		AnnoAnnouncementsExample example = new AnnoAnnouncementsExample();
		Criteria criteria = example.createCriteria();
		//判断查询参数中是否包含过滤条件
		if (anno != null) {
			//过滤条件中是否包含公告ID信息
			Long id = anno.getId();
			if ( id != null) {
			criteria.andIdEqualTo(id);	
			};
			//过滤条件中是否包含公告类型信息
			String type = anno.getType();
			
			if (!StringUtils.isBlank(type)) {
				criteria.andTypeEqualTo(type);
			}
			//过滤条件中是否包含创建时间信息
			Date createtime = anno.getCreatetime();
			if (createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			//过滤条件中是否包含修改时间信息
			Date modifytime = anno.getModifytime();
			if (modifytime != null) {
				criteria.andModifytimeGreaterThanOrEqualTo(modifytime);
			}
			//过滤条件中是否包括学分信息
			Integer score = anno.getScore();
			if(score != null){
				criteria.andScoreEqualTo(score);
			}
			//过滤条件中是否包含标题信息
			String title = anno.getTitle();
			if(StringUtils.isNotBlank(title)) {
				criteria.andTitleLike("%"+title+"%");
			}
			
		}
		//设置排序方式
		example.setOrderByClause(sortName+" "+sortOrder);
		//设置分页
		PageHelper.startPage(page, rows);
		//查询
		List<AnnoAnnouncements> list = annoMapper.selectByExampleWithBLOBs(example);
		
		//获取总记录数
		PageInfo<AnnoAnnouncements> info = new PageInfo<AnnoAnnouncements>(list);
		result.setTotal(info.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public List<AnnoAnnouncements> getAllAnnoList() {
		AnnoAnnouncementsExample example = new AnnoAnnouncementsExample();
		List<AnnoAnnouncements> list = annoMapper.selectByExampleWithBLOBs(example);
		return list;
	}

}
