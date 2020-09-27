package com.scholars.service;

import com.scholars.pojo.Punishments;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

public interface IPunishmentsService {

	EUDataGridResult getPunishmentsGrid(Integer page,Integer rows,String sort,String order,Punishments punishment);
	TaotaoResult CreatePunishment(Punishments punishment );
	TaotaoResult UpdataPunishment(Punishments punishment);
	TaotaoResult DeletePunishment(Long id);
	
}
