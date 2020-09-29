package com.scholars.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scholars.mapper.CourseMapper;
import com.scholars.pojo.Course;
import com.scholars.pojo.CourseExample;
import com.scholars.pojo.CourseExample.Criteria;
import com.scholars.service.ICourseService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.IDUtils;
/**
 * 
	* ClassName: CourseServiceImpl <br/> 
	* Function: TODO 课程管理Service实现类. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-27 10:48:12 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseMapper courseMapper;
	@Override
	public EUDataGridResult getCourseGrid(Integer page, Integer rows, String sort, String order, Course course) {
		EUDataGridResult result = new EUDataGridResult();
		CourseExample example = new CourseExample();
		Criteria criteria = example.createCriteria();
		if(course != null) {
			String author = course.getAuthor();
			if (StringUtils.isNotBlank(author)) {
				criteria.andAuthorLike("%"+author+"%");
			}
			String coursename = course.getCoursename();
			if(StringUtils.isNotBlank(coursename)) {
				criteria.andCoursenameLike("%"+coursename+"%");
			}
			Date createtime = course.getCreatetime();
			if(createtime != null) {
				criteria.andCreatetimeGreaterThanOrEqualTo(createtime);
			}
			Date dateline = course.getDateline();
			if(dateline != null) {
				criteria.andDatelineLessThanOrEqualTo(dateline);
			}
			Long id = course.getId();
			if (id != null) {
				criteria.andIdEqualTo(id);
			}
			String memo = course.getMemo();
			if(StringUtils.isNotBlank(memo)) {
				criteria.andMemoLike("%"+memo+"%");
			}
			Date modifytime = course.getModifytime();
			if(modifytime != null) {
				criteria.andModifytimeGreaterThanOrEqualTo(modifytime);
			}
			String scores = course.getScores();
			if(StringUtils.isNotBlank(scores)) {
				criteria.andScoresEqualTo(scores);
			}
			String studyperson = course.getStudyperson();
			if(StringUtils.isNotBlank(studyperson)) {
				criteria.andStudypersonLike("%"+studyperson+"%");
			}
		}
		example.setOrderByClause(sort+" "+order);
		
		PageHelper.startPage(page, rows);
		List<Course> list = courseMapper.selectByExampleWithBLOBs(example);
		PageInfo<Course> info = new PageInfo<Course>(list);
		
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createCourse(Course course) {
		course.setId(IDUtils.genItemId());
		course.setCreatetime(new Date());
		course.setModifytime(new Date());
		courseMapper.insert(course);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult UpdataCourse(Course course) {
		Long id = course.getId();
		Course course2 = courseMapper.selectByPrimaryKey(id);
		course.setCreatetime(course2.getCreatetime());
		courseMapper.updateByPrimaryKey(course);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult DeleteCourse(Long id) {
		courseMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

}
