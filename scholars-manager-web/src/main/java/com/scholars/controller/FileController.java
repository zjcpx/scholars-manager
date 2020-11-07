package com.scholars.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scholars.pojo.AnnoAnnouncements;
import com.scholars.pojo.Employee;
import com.scholars.service.IAnnosService;
import com.scholars.service.IEmployeeService;
import com.zjcpx.pojo.TaotaoResult;
import com.zjcpx.utils.ExcelUtil;
import com.zjcpx.utils.IDUtils;

/**
 * 
	* ClassName: FileController <br/> 
	* Function: TODO 文件管理Controller. <br/> 
	* Reason: TODO ADD REASON(可选). <br/> 
	* date: 2020-9-14 11:07:06 <br/> 
	* 
	* @author Mike.Cai 
	* @version  
	* @since JDK 1.8
 */

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private IAnnosService annoservice;
	@Autowired
	private IEmployeeService EmployeeService;

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult exportFile(MultipartFile file,Model model) {
		String originalFilename = file.getOriginalFilename();
		String prefix = originalFilename.substring(originalFilename.lastIndexOf('.'));
		int num = 0;
		try {
			File fileName = File.createTempFile(originalFilename, prefix);
			file.transferTo(fileName);
			FileInputStream in = new FileInputStream(fileName);
			List<List<Object>> bankListByExcel = ExcelUtil.getBankListByExcel(in, fileName.getAbsolutePath());
			for (int i = 0; i < bankListByExcel.size(); i++) {
				List<String> annoString = new ArrayList<String>();
				for (int j = 0; j < bankListByExcel.get(i).size(); j++) {
					annoString.add((String) bankListByExcel.get(i).get(j));	
				}
				AnnoAnnouncements announcements = new AnnoAnnouncements();
				announcements.setId(IDUtils.genItemId());
				announcements.setAttachments(annoString.get(5));
				announcements.setContent(annoString.get(2));
				announcements.setCreatetime(new Date());
				announcements.setModifytime(new Date());
				announcements.setScore(Integer.parseInt(annoString.get(4)));
				announcements.setTitle(annoString.get(1));
				announcements.setType(annoString.get(3));
				TaotaoResult result = annoservice.createAnnoAnnouncements(announcements);
				if (result.getStatus() == 200) {
					continue;
				}else {
					return TaotaoResult.build(500, "导入公告错误");
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return TaotaoResult.build(500, "导入公告错误");
		}
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "/EmpInput",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult InputEmployeeFile(MultipartFile empfile,Model model) {
		String originalFilename = empfile.getOriginalFilename();
		String prefix = originalFilename.substring(originalFilename.lastIndexOf('.'));
		int num = 0;
		try {
			File fileName = File.createTempFile(originalFilename, prefix);
			empfile.transferTo(fileName);
			FileInputStream in = new FileInputStream(fileName);
			List<List<Object>> bankListByExcel = ExcelUtil.getBankListByExcel(in, fileName.getAbsolutePath());
			for (int i = 0; i < bankListByExcel.size(); i++) {
				 String empNo = (String)bankListByExcel.get(i).get(1);
				 if (!EmployeeService.isSameNo(empNo)) {
					continue;
				}else {
					List<String> empString = new ArrayList<String>();
					for (int j = 0; j < bankListByExcel.get(i).size(); j++) {
						empString.add((String) bankListByExcel.get(i).get(j));					
					}
					Employee employee = new Employee();
					employee.setId(IDUtils.genItemId());
					employee.setCreatetime(new Date());
					employee.setDep(empString.get(5));
					employee.setEmail(empString.get(8));
					employee.setModifytime(new Date());
					employee.setName(empString.get(2));
					employee.setNickname(empString.get(10));
					employee.setNo(empString.get(1));
					employee.setPassword(DigestUtils.md5DigestAsHex("123".getBytes()));
					employee.setPosition(empString.get(6));
					employee.setQq(empString.get(9));
					employee.setRole(empString.get(7));
					employee.setScore(Integer.valueOf(empString.get(3)));
					employee.setTel(empString.get(4));
					employee.setWechat(empString.get(11));
					TaotaoResult result = EmployeeService.createEmployee(employee);
					if (result.getStatus() != 200) {
						return TaotaoResult.build(500, "导入员工错误1");
					}
					num++;
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return TaotaoResult.build(501, "导入员工错误2");
		}
		return TaotaoResult.build(200,"成功导入"+num+"条记录");
	}
}

