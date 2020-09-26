<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="annoEditForm" class="itemForm" method="post">

	    <input type="hidden" name="id" />
		<!-- <input type="hidden" name="createtime" id="createtime" /> -->

	    <table cellpadding="5">
	        <tr>
	            <td>公告标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>公告类型：</td>
	            <td><input id= "annoType" type="text" name="type" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>公告内容:</td>
	            <td>
	            	<textarea style="width:800px;height:300px;visibility:hidden;" name="content"></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td>公告积分:</td>
	            <td><input class="easyui-numberbox" type="text" name="score" data-options="min:0,max:99,required:true" />
	            </td>
	        </tr>
	       
	        <tr>
	            <td>公告附件:</td>
	            <td>
					<input type="file" name="" id=""><a class="easyui-linkbutton" href="javascript(0)">上传</a>
					<input type="hidden" name="attachments"/>
					<!-- <input type="hidden" name="createtime"/> -->
	            </td>
	        </tr>
	        
	    </table>
	    
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var data = ($('#datagrid').datagrid("getSelections"))[0];
	$('#annoEditForm').form('load',data);

	var createtime = $("#createtime").val();
	var createtime = Conversiontime(parseInt(createtime));
	//2016-10-10 12:10:12

	console.log(createtime);//2020-06-07 22:35:07
	$("#createtime").val(createtime);
	var annoEditForm = $('#annoEditForm');
	//页面初始化完毕后执行此方法
	$(function(){
		$('#annoType').combobox({
			editable:false,
			
			url:'/annotype/annoTypeList',
			valueField:'typename',
			textField:'typename'
		});
		
		//创建富文本编辑器
		//itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		annoEditForm = KindEditor.create("#annoEditForm [name=content]");
		//初始化类目选择和图片上传器
		/*TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			TAOTAO.changeItemParam(node, "annoEditForm");
		}});*/
	});
	//提交表单
	function submitForm(){
		console.log("Post");
		//有效性验证
		if(!$('#annoEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//同步文本框中的商品描述
		annoEditForm.sync();
		
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.ajax({
			type:'post',
			url:'/anno/update',
			data:$('#annoEditForm').serialize(),
			/*dataType:'json',
			contentType: "application/json; charset=utf-8",*/
			success:function(data){
				if(data.status == 200){
					$.messager.alert('提示','公告修改成功!');
					var tabs = $("#tabs");
					var tab = tabs.tabs("close",'编辑公告');
					$('#datagrid').datagrid('load');
				}
			}
		});
	}
	
	function clearForm(){
		$('#annoEditForm').form('reset');
		itemAddEditor.html('');
	}

	function Conversiontime(timestamps) {
		console.log(timestamps);
		var date = new Date(timestamps);
		console.dir(date);
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = (date.getDate()<10?'0'+(date.getDate()):date.getDate()) + ' ';
		h = (date.getHours()<10?'0'+(date.getHours()):date.getHours()) + ':';
		m = (date.getMinutes()<10?'0'+(date.getMinutes()):date.getMinutes())+':';
		s = (date.getSeconds()<10?'0'+(date.getSeconds()):date.getSeconds());
		return Y+M+D+h+m+s;
	}

</script>
