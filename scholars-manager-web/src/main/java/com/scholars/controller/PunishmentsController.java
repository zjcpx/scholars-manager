package com.scholars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scholars.pojo.Punishments;
import com.scholars.service.IPunishmentsService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/**
 * 
	* ClassName: PunishmentsController <br/> 
	* Function: TODO ADD 处罚管理controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-24 14:08:46 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */
@Controller
@RequestMapping("/Punishments")
public class PunishmentsController {

	@Autowired
	private IPunishmentsService punishmentsService;
	
	@RequestMapping("/grid")
	@ResponseBody
	public EUDataGridResult getPunishmentGrid(Integer page,Integer rows,String sort,String order,Punishments punishment) {
		EUDataGridResult result = punishmentsService.getPunishmentsGrid(page, rows, sort, order, punishment);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult CreatePunishment(Punishments punishment) {
		TaotaoResult result = punishmentsService.CreatePunishment(punishment);
		return result;
	}
}
