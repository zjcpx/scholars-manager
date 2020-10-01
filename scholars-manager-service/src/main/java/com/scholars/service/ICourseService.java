package com.scholars.service;

import com.scholars.pojo.Course;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

public interface ICourseService {
	
	EUDataGridResult getCourseGrid(Integer page,Integer rows,String sort,String orderString,Course course);
	TaotaoResult createCourse(Course course);
	TaotaoResult UpdataCourse(Course course);
	TaotaoResult DeleteCourse(Long ids);
	

}
