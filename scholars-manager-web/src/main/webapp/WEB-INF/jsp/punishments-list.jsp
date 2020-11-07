<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="easyui-layout" border='false' fit='true'>
	<!-- 过滤窗口 -->
	<div region = 'north' border = 'false' title="过滤" style='height: 110px;overflow: hidden;'>
		<form id="role_search">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%">
				<tr>
					<th style="width: 70px;text-align: right;">编号</th>
					<td><input style="width: 180px" type="text" name="id" id="id" class="easyui-numberbox"></td>
					<th style="width: 70px;text-align: right;">处罚原因</th>
					<td><input style="width: 180px" type="text" name="resone" id="resone" class="easyui-textbox"></td>
					<th style="width: 70px;text-align: right;">处罚描述</th>
					<td><input style="width: 180px" type="text" name="describ" id="describ" class="easyui-textbox"></td>
					
				</tr>
				
				<tr>
					<th style="width: 70px;text-align: right;">处罚结果</th>
					<td><input style="width: 180px" type="text" name="result" id="result" class="easyui-textbox"></td>
					<th style="width: 70px;text-align: right;">被处罚人</th>
					<td><input style="width: 180px" type="text" name="person" id="person" class="easyui-textbox"></td>
					<th style="width: 70px;text-align: right;">创建时间</th>
					<td>
						<input id = "createtime" style="width: 180px" name='createtime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
					
					<th >
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'punishmentSearch();'>根据条件查询</a>
					</th>
					<th>
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'punishmentRest()' >清空查询条件</a>
					</th>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格 -->
	<div region = 'center' border = 'false' >
		<table id = 'punishmentDatagrid' style="height: 100%">
		</table>
	</div>
</div>
<!-- 右键菜单 -->
<div id = "annoOpMenu" class="easyui-menu" style="width:80px;display:none;">
	<div onclick = 'addPosition()' iconCls = 'icon-add'>新建</div>
</div>
<!-- 导入Excel对话框 -->
<div id="inputDialog" title ="">
	<form id="inputForm"  method="post" enctype="multipart/form-data" style=" margin-left:30px" >
		<input type="file" name="file" id="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" style="height: 25px;width:200px;margin-top:15px;" class="easyui-filebox">
	</form>
</div>

<script type="text/javascript" charset="utf-8">


	//当前正在编辑的行，默认为undefined；
	var currEditRow = undefined;
	//公告类型下拉框
	$('#type').combobox({
		editable:false,
		url:'/annotype/annoTypeList',
		valueField:'typename',
		textField:'typename'
	});

	
	//获取选择的公告列表
	function getSelections(){
		var ids = $('#datagrid').datagrid("getSelections");
		return ids;
	}

	//根据检索条件查找匹配的公告内容
	punishmentSearch = function (){
		//获取指定的创建时间
		var createdate = $("#createtime").datetimebox('getValue');
		var time1 = createdate;
		//如果查询条件中的创建时间不未空
		if(createdate != ""){
			//将创建时间格式成时间戳格式的时间对象
			createdate = new Date(new Date(createdate).getTime());
		}else{	//创建时间没有指定，将查询时间指定未空
			createdate = new Date(null);
		}
		//将查询条件中创建时间设置为时间对象（保证时间可以被后台接受）
		$("#createtime").textbox('setValue',createdate);
		
		$('#punishmentDatagrid').datagrid('load',serializeToObject($('#role_search').form()));
		//为保持页面显示的友好
		$("#createtime").textbox('setValue',time1);
		
	}
		
	//清空查询条件
	punishmentRest = function(){
		//把过滤条件中的内容置空
		$('#role_search ').form().find("input").val("");
		$('#punishmentDatagrid').datagrid('load',{});
	}
		
	//增加公告
	addPosition = function(){
		var tabs = $("#tabs");
		var tab = tabs.tabs("getTab",'新增处罚记录');
		if(tab){
			tabs.tabs("select",'新增处罚记录');
		}else{
	        tabs.tabs('add',{
	            title:'新增处罚记录',
	            href: 'punishments-add',
	            closable:true,
	            bodyCls:"content"
	        });
	    }
	}

	//初始化公告列表
	$(function(){
		$("#punishmentDatagrid").datagrid({
			title:'处罚记录明细',
			url:"/Punishments/grid",
			pagination:true,
			pageSize:30,
			pageList:[30,60,90],
			fit:true,
			fitColumns:true,
			nowrap:true,
			border:true,
			idfield:'id',
			sortName:'createtime',
			sortOrder:'desc',
			striped:true,
			columns:[[
				{field:'id',width:80,title:'序号',checkbox:true},
				{field:'person',width:100,title:'被处罚人',sortable:true},
				{field:'resone',width:80,title:'处罚原因',sortable:true},
				{field:'describ',width:80,title:'事件描述',sortable:true},
				{field:'result',width:80,title:'处罚结果',sortable:true},
				{field:'createtime',width:70,title:'处罚时间',sortable:true,formatter:function(param){
					return new Date(param).format("yyyy/MM/dd hh:mm:ss");
				}},
				
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					addPosition();
				}
			},'-',{
				text:'导出Excel',
				iconCls:'icon-edit',
				handler:function(){

					$("#datagrid").datagrid('toExcel','Punishments.xls');
				}
			}],	
		});
		$('.datagrid-header div').css('textAlign', 'center');
	})
	
	
</script>

