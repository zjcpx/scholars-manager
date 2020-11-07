package com.scholars.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scholars.mapper.AnnoTypeMapper;
import com.scholars.pojo.AnnoAnnouncementsExample;
import com.scholars.pojo.AnnoType;
import com.scholars.pojo.AnnoTypeExample;
import com.scholars.service.IAnnoTypeService;

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

}
