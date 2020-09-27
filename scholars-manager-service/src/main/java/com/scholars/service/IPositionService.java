package com.scholars.service;

import java.util.List;

import com.scholars.pojo.Position;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

public interface IPositionService {

	List<Position> getPositionList();
	EUDataGridResult getPositionGrid(Integer page,Integer rows,String sort,String sord,Position position);
	TaotaoResult CreatePosition(Position position);
	TaotaoResult updataPosition(Position position);
	TaotaoResult deletePosition(Long id);
	Boolean isSamePosiName(String posiName);
}
