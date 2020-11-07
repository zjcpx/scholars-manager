package com.scholars.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.scholars.pojo.Position;
import com.scholars.service.IPositionService;
import com.zjcpx.pojo.EUDataGridResult;
import com.zjcpx.pojo.TaotaoResult;

/**
 * 
	* ClassName: PositionController <br/> 
	* Function: TODO 职位管理Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-18 15:27:25 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */

@Controller
@RequestMapping("/Position")
public class PositionController {

	@Autowired
	private IPositionService positionService;
	
	//将字符串转换为Date类
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        
    }
    
	@RequestMapping("/list")
	@ResponseBody
	public List<Position> getAllPositionList(){
		List<Position> list = positionService.getPositionList();
		return list;
	}
	
	@RequestMapping("/grid")
	@ResponseBody
	public EUDataGridResult getPositionGrid(Integer page,Integer rows,String sort,String order,Position position) {
		EUDataGridResult result = positionService.getPositionGrid(page, rows, sort, order, position);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult createPosition(Position position) {
		String string = position.getPositionname();
		if(positionService.isSamePosiName(string)) {
			TaotaoResult result = positionService.CreatePosition(position);
			return result;
		}
		return TaotaoResult.build(500, "职位名称重复");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deletePosition(String ids) {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			Long longId = Long.valueOf(id[i]);
			TaotaoResult result = positionService.deletePosition(longId);
			if (result.getStatus() != 200) {
				return TaotaoResult.build(500, "删除职位失败");
			}
		}
		return TaotaoResult.ok();
	}
	
	@RequestMapping("/updata")
	@ResponseBody
	public TaotaoResult updataPosition(Position position) {
		String positionname = position.getPositionname();
		if (positionService.isSamePosiName(positionname)) {
			TaotaoResult result = positionService.updataPosition(position);
			return result;
		}
		return TaotaoResult.build(500, "职位名称重复");
	}
}
