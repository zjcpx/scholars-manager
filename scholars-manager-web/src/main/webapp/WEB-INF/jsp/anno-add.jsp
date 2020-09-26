<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="annoAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>公告标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>公告类型：:</td>
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
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传附件</a>
	                 <input type="hidden" name="attachments"/>
	            </td>
	        </tr>
	        
	    </table>
	    <input type="hidden" name="itemParams"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var annoAddEditor = $('#annoAddForm');
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
		annoAddEditor = KindEditor.create("#annoAddForm [name=content]", TT.kingEditorParams);
		//初始化类目选择和图片上传器
		TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			TAOTAO.changeItemParam(node, "annoAddForm");
		}});
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#annoAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//同步文本框中的商品描述
		annoAddEditor.sync();
		//取商品的规格
		var paramJson = [];
		/*$("#annoAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});*/
		//把json对象转换成字符串
		/*paramJson = JSON.stringify(paramJson);
		$("#annoAddForm [name=itemParams]").val(paramJson);*/
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/anno/save",$("#annoAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增公告成功!');
				var tabs = $("#tabs");
				var tab = tabs.tabs("close",'新增公告');
				$('#datagrid').datagrid('load');
			}
		});
	}
	
	function clearForm(){
		$('#annoAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
