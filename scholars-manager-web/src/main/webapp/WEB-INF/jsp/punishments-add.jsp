<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="annoPunishmentsForm" class="itemForm" method="post">
		<input type="hidden" id="id" name="id">
		<input class="" id="persons" name="person" style="display: none;"></input>
	    <table cellpadding="5">
	        <tr>
	            <td width="100px">被处罚人：</td>
	            <td>
	            	<input class="easyui-combobox" id="tttt" type="text" ></input>
	            	<input type="text" id="ttttt" style="display: none" />
	            </td>
	        </tr>
	        <tr>
	            <td>处罚原因：</td>
	            <td><input  class="easyui-textbox" id= "resone" type="" name="resone" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>事件描述：</td>
	            <td>
	            	<textarea style="width:730px;height:300px;" name="describ" id="describ"></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td>处罚结果:</td>
	            <td><input class="easyui-numberbox" type="easyui-text" name="result" id="result" data-options="min:0,max:99,required:true" />
	            </td>
	        </tr>
	       
	      <!--   <tr>
	            <td>处罚时间:</td>
	            <td>
	                 <input class="easyui-datetimebox" name="createtime" id="createtime" />
	            </td>
	        </tr> -->
	        
	    </table>
	    
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	$('#tttt').combobox({
		editable:false,
		url:'/Deportment/list',
		valueField:'depname',
		textField:'depname',

		onSelect: function(rec){
			$.post('/Employee/employees',{depName:rec.depname},function(data){
				
				if (data != null) {
					$("#ttttt").combobox({
						editable:false,
						data:data,
						valueField:'no',
						textField:'name',
						onSelect:function(record){
							$("#persons").val(record.no);
						}
					});
				}
			})	
        }
	});
	// console.log();

	var annoPunishmentsEditor = $('#annoPunishmentsForm');
	//页面初始化完毕后执行此方法
	$(function(){
		
		//创建富文本编辑器
		//itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
		annoPunishmentsEditor = KindEditor.create("#annoPunishmentsForm [name=describ]", TT.kingEditorParams);
		//初始化类目选择和图片上传器
		TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			TAOTAO.changeItemParam(node, "annoPunishmentsForm");
		}});
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#annoPunishmentsForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		//同步文本框中的商品描述
		annoPunishmentsEditor.sync();
		//取商品的规格
		var paramJson = [];
		

		$.post("/Punishments/save",$("#annoPunishmentsForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增公告成功!');
				var tabs = $("#tabs");
				var tab = tabs.tabs("close",'新增公告');
				$('#punishmentDatagrid').datagrid('load');
			}
		});
	}
	
	function clearForm(){
		$('#annoPunishmentsForm').form('reset');
		itemAddEditor.html('');
	}
</script>
