<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="easyui-layout" border='false' fit='true'>
	<!-- 过滤窗口
 private Long id;

    private String positionname;

    private Date createtime;

    private Date modifytime;
	 -->
	<div region = 'north' border = 'false' title="过滤" style='height: 110px;overflow: hidden;'>
		<form id="role_search">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%">
				<tr>
					<th style="width: 70px;text-align: right;">职位编号</th>
					<td><input style="width: 180px" type="text" name="id" id="id" class="easyui-numberbox"></td>
					<th style="width: 70px;text-align: right;">职位名称</th>
					<td><input style="width: 180px" type="text" name="positionname" id="positionname" class="easyui-textbox"></td>
					<th style="width: 70px;text-align: right;">创建时间</th>
					<td>
						<input id = "createtime" style="width: 180px" name='createtime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
				</tr>
				
				<tr>
					<th style="width: 70px;text-align: right;">修改时间</th>
					<td>
						<input id = "modifytime" style="width: 180px" name='modifytime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
					<th >
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'positionSearch();'>根据条件查询</a>
					</th>
					<th>
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'positionRest()' >清空查询条件</a>
					</th>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格 -->
	<div region = 'center' border = 'false' >
		<table id = 'positionDatagrid' style="height: 100%">
		</table>
	</div>
</div>
<!-- 右键菜单 -->
<div id = "annoOpMenu" class="easyui-menu" style="width:80px;display:none;">
	<div onclick = 'addPosition()' iconCls = 'icon-add'>新建</div>
	<div onclick = 'editPosition()' iconCls = 'icon-edit'>编辑</div>
	<div onclick = 'removePosition()' iconCls = 'icon-remove'>删除</div>
</div>
<!-- 导入Excel对话框 -->
<div id="inputDialog" title ="">
	<form id="inputForm"  method="post" enctype="multipart/form-data" style=" margin-left:30px" >
		<input type="file" name="file" id="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" style="height: 25px;width:200px;margin-top:15px;" class="easyui-filebox">
	</form>
</div>

<script type="text/javascript" charset="utf-8">

	//导入对话框
	$("#inputDialog").dialog({
		title: '导入Excel',    
	    width: 300,    
	    height: 120,    
	    closed: true,    
	    cache: false,    
	    modal: true,
	    buttons:[
	    {
	    	text:'导入',
			handler:function(){

				var formData = new FormData($( "#inputForm" )[0]);
				 $.ajax({
					type: "post",
					url: '/file/input',
		              //传入组装的参数
					data:formData,
					dataType: "json",
					async: false,
					cache: false,   //上传文件不需要缓存
					contentType: false,  //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
					processData: false,  //需设置为false。因为data值是FormData对象，不需要对数据做处理
					success: function (data) {
						if(data.status === 200){
							$.messager.alert("提示","文件导入成功","success");
							$("#inputDialog").window('close');
							$("#datagrid").datagrid("load");
						}else{
							//失败
						}
					},
					error:function(){
						;		     
					}
				});
			}

	    },
	    {
			text:'放弃',
			handler:function(){
				$("#inputDialog").window('close');
			}
	    }] 
	});

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
	positionSearch = function (){
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
		//处理修改时间的格式问题，方法同“创建日期”
		var modifytime = $("#modifytime").datetimebox('getValue');
		//console.log(typeof(modfytime));
		var time2 = modifytime;
		if(modifytime != ""){	
			modifytime = new Date(new Date(modifytime).getTime());	
		}else{
			modifytime = new Date(null);
		}
		$("#modifytime").textbox('setValue',modifytime);
		$('#positionDatagrid').datagrid('load',serializeToObject($('#role_search').form()));
		//为保持页面显示的友好
		$("#createtime").textbox('setValue',time1);
		$("#modifytime").textbox('setValue',time2);
	}
		
	//清空查询条件
	positionRest = function(){
		//把过滤条件中的内容置空
		$('#role_search ').form().find("input").val("");
		$('#positionDatagrid').datagrid('load',{});
	}
		
	//增加公告
	addPosition = function(){
		if (currEditRow != undefined) {
			confirm($("#positionDatagrid"),currEditRow);
		}else{
			$("#positionDatagrid").datagrid('insertRow',{
				index: 0,	// 索引从0开始
				row: {
					createtime: new Date(),
					modifytime:new Date()
				}
			})
			$("#positionDatagrid").datagrid('beginEdit',0);
			currEditRow = 0;
		}
	}

	//删除公告
	removePosition = function(){
		var roleIds = $("#positionDatagrid").datagrid('getSelections');
		if(roleIds.length < 1){
			$.messager.alert('提示','请选择需要删除的职位','info');
			return;
		}else{

			var ids = [];
			var positionName = [];
			for (var i in roleIds) {
				ids.push(roleIds[i].id);
				positionName.push(roleIds[i].positionname);
			}
			var idString = ids.join(',');

			positionName.join(',');
			$.messager.confirm('确认','请确认是否要删除”'+positionName+'“职位?',function(r){
				if(r){
					$.post('/Position/delete',{ids:idString}, function(data){
						if (data.status == 200) {
							$.messager.alert('提示','职位删除成功');
							$("#positionDatagrid").datagrid('load');
							$("#positionDatagrid").datagrid('acceptChanges');
							$("#positionDatagrid").datagrid('unselectAll');
							currEditRow = undefined;
							
						}
					})
				}
			})
		}
	}

	//编辑公告
	editPosition = function(){
		if (currEditRow != undefined) {
			confirm($("#positionDatagrid"),currEditRow);
		}else{
			var rows = $("#positionDatagrid").datagrid('getSelections');
			if(rows.length > 1 ){
				$.messager.alert('提示','一次只能修改一个职位');
			}else if(rows.length == 0){
				$.messager.alert('提示','请选择要编辑的职位');
			}else{
				var index = $("#positionDatagrid").datagrid("getRowIndex",rows[0]);
				$("#positionDatagrid").datagrid("beginEdit",index);
				currEditRow = index;
			}
		}
	}
	//初始化公告列表
	$(function(){
		$("#positionDatagrid").datagrid({
			title:'职位明细',
			url:"/Position/grid",
			pagination:true,
			pageSize:30,
			pageList:[30,60,90],
			fit:true,
			fitColumns:true,
			nowrap:true,
			border:true,
			idfield:'id',
			sortName:'modifytime',
			sortOrder:'desc',
			striped:true,
			columns:[[
			/*private String positionname;

			    private Date createtime;

			    private Date modifytime;*/
				{field:'Id',width:80,title:'序号',checkbox:true},
				{field:'positionname',width:100,title:'职位名称',sortable:true,editor:{
						type:'validatebox',
						options:{
							required:true,
						}
					},
				},
				
				{field:'createtime',width:70,title:'初建时间',sortable:true,formatter:function(param){
					return new Date(param).format("yyyy/MM/dd hh:mm:ss");
				}},
				{field:'modifytime',width:70,title:'修改时间',sortable:true,formatter:TAOTAO.formatDateTime},
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					addPosition();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					removePosition();
				}
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					editPosition();
				}
			},'-',{
				text:'保存修改',
				iconCls:'icon-save',
				handler:function(){
					$("#positionDatagrid").datagrid('endEdit',currEditRow);
				}
			},'-',{
				text:'放弃修改',
				iconCls:'icon-cancel',
				handler:function(){
					$("#positionDatagrid").datagrid('rejectChanges');
					$("#positionDatagrid").datagrid('unselectAll');
					currEditRow = undefined;
				}
			}/*,'-',{
				text:'导入Excel',
				iconCls:'icon-search',
				handler:function(){
					
					$("#inputDialog").window('open');
					$("#inputDialog").window('center');
				}
			},'-',{
				text:'导出Excel',
				iconCls:'icon-edit',
				handler:function(){

					$("#datagrid").datagrid('toExcel','Announcements.xls');
				}
			}*/],
			onAfterEdit : function (rowIndex,rowData,changs){
				var insert = $("#positionDatagrid").datagrid('getChanges','inserted');
				var update = $("#positionDatagrid").datagrid('getChanges','updated');
				var url = '';
				var message = '';
				if (insert.length >0) {
					url = '/Position/save';
					message = '添加职位成功';
				}
				if(update.length > 0){
					url = '/Position/updata';
					message = '更新职位成功';
					rowData.createtime = new Date(rowData.createtime);
					rowData.modifytime = new Date(rowData.modifytime);
				}
				$.post(url,rowData,function(data){
					if (data.status == 200) {
						$.messager.alert('提示',message);
						currEditRow = undefined;
						$("#positionDatagrid").datagrid('unselectAll');
					}else{
						$.messager.alert('提示',data.get(msg));
					}
				})
				
			},
			
			onDblClickRow:function(index, row){
				if(currEditRow != undefined){
					$("#positionDatagrid").datagrid('endEdit',currEditRow);
				}else{
					$("#positionDatagrid").datagrid('beginEdit',index)
					$("#positionDatagrid").datagrid('selectRow',index)
					currEditRow = index;
				}
				
			}, 
			
			onClickRow:function(index,row){
				if(currEditRow != undefined){
					$("#datagrid").datagrid('endEdit',currEditRow);
				}
				
			},
			onRowContextMenu:function(e, index, row){
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',index);
				$('#annoOpMenu').menu('show',{
					top:e.pageY,
					left:e.pageX
				})
			}
		});
		$('.datagrid-header div').css('textAlign', 'center');
	})
	
	
</script>

