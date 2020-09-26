<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding: 10px 10px 10px 10px">
	<form id="itemeEditForm" class="itemForm" method="post">
		<table cellpadding="5">

			<tr>
				<td>员工编号:</td>
				<td><input class="easyui-textbox" type="text" name="no"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><input class="easyui-textbox" type="text" name="name"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			
			<tr>
				<td>电话:</td>
				<td><input class="easyui-textbox" type="text" name="tel"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>电子邮箱:</td>
				<td><input class="easyui-textbox" type="text" name="email"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>QQ号码:</td>
				<td><input class="easyui-textbox" type="text" name="qq"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>所属部门:</td>
				<td><input id="dept" name="dep" value="请选择"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>职位:</td>
				<td><input id="position" name="position" value="请选择"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>角色:</td>
				<td><input id="role" name="role" value="请选择"
					data-options="required:true" style="width: 280px;"></input></td>
			</tr>

		</table>

	</form>
	<div style="padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">提交</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script>
$(document).ready(function(){
	 $("#dept").combobox({
	        url:'/dept/list',
	        valueField:'depName',
	        textField:'depName'
	    });
	 $("#role").combobox({
	        url:'/role/list2',
	        valueField:'roleName',
	        textField:'roleName'
	    });
	 $("#position").combobox({
	        url:'/position/list2',
	        valueField:'positionname',
	        textField:'positionname'
	    });
	});
</script>
<script type="text/javascript">
	var itemAddEditor;
	//页面初始化完毕后执行此方法
	$(function() {
		//创建富文本编辑器
		//itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		itemAddEditor = KindEditor.create("#itemAddForm [name=desc]",
				TT.kingEditorParams);
		//初始化类目选择和图片上传器
		TAOTAO.init({
			fun : function(node) {
				//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
				TAOTAO.changeItemParam(node, "itemAddForm");
			}
		});
	});
	//提交表单
	function submitForm() {
		//有效性验证
		if (!$('#itemeEditForm').form('validate')) {
			$.messager.alert('提示', '表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/employee/edit", $("#itemeEditForm").serialize(), function(data) {
			
			if (data.status == 200) {
				$.messager.alert('提示', '新增商品成功!');
				$('#itemeEditForm').window('close');
				$('#itemList').datagrid("reload");
			}else{
				$.messager.alert('提示', data.msg);
			}
		});
	}

	function clearForm() {
		$('#itemeEditForm').form('reset');
		itemAddEditor.html('');0
	}
</script>
