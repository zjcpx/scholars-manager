<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="courseEditForm" class="itemForm" method="post">
		<input type="hidden" name="id" id="id">
	    <table cellpadding="5">
	        <tr>
	            <td>课程名称：</td>
	            <td><input class="easyui-textbox" type="text" name="coursename" id="coursename" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>课程学分：</td>
	            <td><input id= "scores" class="easyui-textbox" type="text" name="scores" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>课程内容:</td>
	            <td>
	            	<textarea style="width:800px;height:300px;visibility:hidden;" name="coursecontents" data-options="required:true"></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td>课程类型:</td>
	            <td>
	            	<td><input id= "coursetype" class="easyui-textbox" type="text" name="coursetype" data-options="required:true" style="width: 280px;"></input></td></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td>必修人员:</td>
	            <td>
	            	部门：<div id="roleSelect" style="display: inline-block;  margin-right: 10px">hello</div><br/>
	            	<input class="easyui-textbox" name="studyperson" id="sperson" data-options="required:true" style="width: 380px"/>
	            </td>
	        </tr>
	       
	        <tr>
	            <td>完成时间:</td>
	            <td>
	            	
	                 <input class="easyui-datetimebox" type= "text" name = "dateline" class= "easyui-datebox" required ="required" editable="false"/>
	            </td>
	        </tr>
	        <tr><td>课程作者</td>
	        	<td>
	        		<input class="easyui-textbox" name="author" id="author" data-options="required:true"/>
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
	

	
	$(function(){
		var data = ($('#coursedatagrid').datagrid("getSelections"))[0];
		$('#courseEditForm').form('load',data);

		getRoleList();
		//创建富文本编辑器
		//itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		courseEditForm = KindEditor.create("#courseEditForm [name=coursecontents]");
		//初始化类目选择和图片上传器
		/*TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			TAOTAO.changeItemParam(node, "courseEditForm");
		}});*/
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#courseEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//同步文本框中的商品描述
		courseEditForm.sync();
		
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.ajax({
			type:'post',
			url:'/Course/updata',
			data:$('#courseEditForm').serialize(),
			/*dataType:'json',
			contentType: "application/json; charset=utf-8",*/
			success:function(data){
				if(data.status == 200){
					$.messager.alert('提示','课程修改成功!');
					var tabs = $("#tabs");
					var tab = tabs.tabs("close",'编辑课程');
					$('#coursedatagrid').datagrid('load');
				}
			}
		});
	}

	function getRoleList(){
		var roleHtml = '';
		var prefix = '<input class= "rolename" type="checkbox" name="roleSelector" value="';
		var suffix = '" /input>';
		$.post('/Role/list',null, function(data){
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++){
					var role = data[i].rolename;
					roleHtml+=prefix+role+' "label="'+role+suffix+role;
				}
			}
			$("#roleSelect")[0].innerHTML = roleHtml;
			$(".rolename").click(function(event) {
				var roleArray = document.getElementsByName("roleSelector");
				console.log(roleArray.length);
				var roleList = [];
				for(var j=0; j < roleArray.length; j++){
					if(roleArray[j].checked == true){
						roleList.push(roleArray[j].value);
					};
				}
				var roleListString = roleList.join(",");
				console.log(roleListString);
				//必修人员名单
				var nameList = [];
				$.post('/Employee/nameList',{roleName:roleListString},function(data){
					if(data.length > 0){
						for(var i = 0; i < data.length; i++){
							if($("input[type='checkbox']").is(':checked')){	
								nameList.push(data[i].name);
							}else{
								nameList.pop(data[i].name);
							};
						}
						var name = nameList.join(",");
						$("#sperson").textbox("setValue",name);
					}
				})
			});
		});	
	}

	
	function clearForm(){
		$('#courseEditForm').form('reset');
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
