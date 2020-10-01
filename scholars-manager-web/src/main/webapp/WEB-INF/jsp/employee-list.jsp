<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" border='false' fit='true'>
	<div region = 'north' border = 'false' title="过滤" style='height: 110px;overflow: hidden;'>
		<form id="employee_search">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%">
				<tr>
					<th style="width: 70px;text-align: right;">员工编号</th>
					<td><input style="width: 180px" type="text" name="no" id="no" ></td>
					<th style="width: 70px;text-align: right;">用户名</th>
					<td><input style="width: 180px" type="text" name="name" id="name"></td>
					<th style="width: 70px;text-align: right;">电话</th>
					<td><input style="width: 180px" type="text" name="tel" id="tel" ></td>
					<th style="width: 70px;text-align: right;">电子邮箱</th>
					<td><input style="width: 180px" name="email" id="email" ></td>
				</tr>
				<tr>
					<th style="width: 70px;text-align: right;">QQ号码</th>
					<td><input style="width: 180px" type="text" name="qq" id="qq"></td>
					<th style="width: 70px;text-align: right;">所属部门</th>
					<td><input style="width: 180px" type="text" name="dep" id="dep"></td>
					<th style="width: 70px;text-align: right;">职位</th>
					<td><input style="width: 180px" type="text" name="position" id="position"></td>
					<th style="width: 70px;text-align: right;">角色</th>
					<td><input style="width: 180px" type="text" name="role" id="role"></td>
				</tr>
				<tr>
					<th style="width: 80px;text-align: right;">现有学习积分</th>
					<td><input style="width: 180px" type="text" name="score" id="score"></td>
					<th style="width: 70px;text-align: right;">创建时间</th>
					<td>
						<input style="width: 180px" name='createtime' class='easyui-datetimebox' id="createtime" editable='false'/>
					</td>
					<th style="width: 70px;text-align: right;">修改时间</th>
					<td>
						<input style="width: 180px" name='modifytime' class='easyui-datetimebox' id="modifytime" editable='false'/>
					</td>
					<th >
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'employeeSearch();'>根据条件查询</a>
					</th>
					<th>
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'cleanSearch()' >清空查询条件</a>
					</th>
				</tr>
			
			</table>
		</form>
	</div>
	<div region = 'center' border = 'false'>
		<table id = 'empdatagrid'></table>
	</div>
</div>
<!-- 对话框 -->
<div id="empInputDialog" title ="">
	<form id="empInputForm"  method="post" enctype="multipart/form-data" style=" margin-left:30px" >
		<input type="file" name="empfile" id="empfile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" style="height: 25px;width:200px;margin-top:15px;" class="easyui-filebox">
	</form>
</div>

<script type="text/javascript" charset="utf-8">

		$("#empInputDialog").dialog({
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

				var formData = new FormData($( "#empInputForm" )[0]);
				 $.ajax({
					type: "post",
					url: '/file/EmpInput',
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
							$("#empInputDialog").window('close');
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
				$("#empInputDialog").window('close');
			}
	    }] 
	});

	//部门下来菜单
	$("#dep").combobox({    
		url:'/Deportment/list',    
	    valueField:'depname',    
	    textField:'depname'
	});
	//职位下来菜单
	$("#position").combobox({
		url:'/Position/list',    
	    valueField:'positionname',    
	    textField:'positionname'
	});
	//角色下拉菜单
	$("#role").combobox({
		url:'/Role/list',
		valueField:'rolename',    
		textField:'rolename'
	})
	
	$(function(){

		var currentEditRow = undefined;
		
		$("#empdatagrid").datagrid({
			title:'员工列表',
			url:'/Employee/Datagrid',
			pagination:true,
			pageSize:30,
			pageList:[30,60,90],
			/* fit:true,
			fitColumns:true, */
			nowarp:false,
			border:false,
			idfield:'id',
			sortName:'score',
			sortOrder:'desc',
			striped:true,
			frozenColumns:[[
				{field:'id',width:120,title:'序号',sortable:true,checkbox:true},
				{field:'no',width:60,title:'员工编号',sortable:true,
					editor:{
						type:'numberbox',
						options:{
							required:true,
							validType:'length[1,5]',
							missingMessage:'请输入工号',
							invalidMessage:'您输入的工号不合规（1至五位数字）',
							onValidate:function(valid){
								console.log(valid);
							}
						},
					}
				},
				{field:'name',width:80,title:'姓名',sortable:true,editor:{
					type:'validatebox',
					options:{
						required:true,
						validType:'length[1,5]',
						missingMessage:'请输入员工姓名',
						}
				}},
			]],
			columns:[[
				{field:'score',width:100,title:'现有学习积分',sortable:true,editor:{
					type:'numberbox',
					options:{
						required:true,
						min:0,
						max:99,
						precision:0,
						missingMessage:'请输入学习积分',
						invalidMessage:'积分需大于0，小于100'
					}
				}},
				{field:'tel',width:100,title:'电话',
					editor:{
						type:'validatebox',
						options:{
							required:true,
							missingMessage:'请输入联系电话',
							
						}
					}
				},
				
				{field:'dep',width:100,title:'所属部门',sortable:true,
					editor:{
						type:'combobox',
						options:{
							required:true,
							editable:false,
							url:'/Deportment/list',    
						    valueField:'depname',    
						    textField:'depname'
						}
					}
				},
				{field:'position',width:50,title:'职位',sortable:true,
					editor:{
						type:'combobox',
						options:{
							required:true,
							editable:false,
							url:'/Position/list',    
						    valueField:'positionname',    
						    textField:'positionname'
						}
					}
				},
				{field:'role',width:50,title:'角色',sortable:true,
					editor:{
						type:'combobox',
						options:{
							required:true,
							editable:false,
							url:'/Role/list',
							valueField:'rolename',    
							textField:'rolename'
							
						}
					}
				},
				{field:'email',width:200,title:'电子邮箱',
					editor:{
						type:'validatebox',
						options:{
							validType:'email',
							missingMessage:'请输入邮箱',
							invalidMessage:'您输入的邮箱不合规，请修改'
						}
					}
				},
				{field:'qq',width:100,title:'QQ号码',
					editor:{
						type:'textbox',
						options:{}
					}
				},
				{field:'nickname',width:100,title:'昵称',
					editor:{
						type:'textbox',
						options:{}
					}
				},
				{field:'wechat',width:100,title:'微信号',
					editor:{
						type:'textbox',
						options:{}
					}
				},
				{field:'createtime',width:100,title:'创建时间',sortable:true,
					formatter:function(param){
						return new Date(param).format("yyyy/MM/dd hh:mm:ss");
					
					}
				},
				{field:'modifytime',width:100,title:'修改时间',sortable:true,
					formatter:function(param){
						return new Date(param).format("yyyy/MM/dd hh:mm:ss");					
					}
				},			
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					if (currentEditRow != undefined) {
						
						$("#empdatagrid").datagrid('endEdit',currentEditRow);

					}else{
						$("#empdatagrid").datagrid('insertRow',{
							index:0,
							row:{
								/*'createtime': new Date(),
								'modifytime': new Date()*/
							}
						});
						$("#empdatagrid").datagrid('beginEdit',0);
						currentEditRow = 0;
					}
					
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					if(currentEditRow != undefined){
						confirm($("#empdatagrid"),currentEditRow);
					}else{
						var rows = $("#empdatagrid").datagrid("getSelections");
						if(rows.length == 0){
							$.messager.alert('提示','请选择要删除的记录');
							return;
						}else{
							var ids = [];
							var empNo = [];
							for(var i in rows){
								ids.push(rows[i].id);
								empNo.push(rows[i].no);
							}
							var id = ids.join(',');
							var empNos = empNo.join(',');
							$.messager.confirm('确认','是否确认要删除编号为：【'+empNos+'】的员工？',function(r){
								if (r){
									$.post('/Employee/delete',{ids:id},function(data){
										if(data.status == 200){
											$('#empdatagrid').datagrid('load');
											$('#empdatagrid').datagrid('acceptChanges');
											$('#empdatagrid').datagrid('unselectAll');
											currentEditRow = undefined;
											$.messager.alert('提示','删除记录成功！');
										}
									})
								}
							})
						}
					}
				}
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					if (currentEditRow != undefined) {
						confirm($("#empdatagrid"),currentEditRow);
					}else{
						var row = $("#empdatagrid").datagrid("getSelections");
						if (row.length > 1) {
							$.messager.alert('提示','同时只能编辑一条记录');
							return;
						}else if(row.length == 0){
							$.messager.alert('提示','请选择要编辑的记录');
							return;
						}else{
							var index = $('#empdatagrid').datagrid('getRowIndex',row[0]);
							$('#empdatagrid').datagrid('beginEdit',index);
							currentEditRow = index;
						}
					}	
				}
			},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					$("#empdatagrid").datagrid('endEdit',currentEditRow);
				}
			},'-',{
				text:'导入Excel',
				iconCls:'icon-search',
				handler:function(){
					
					$("#empInputDialog").window('open');
					$("#empInputDialog").window('center');
				}
			},'-',{
				text:'导出Excel',
				iconCls:'icon-edit',
				handler:function(){

					$("#empdatagrid").datagrid('toExcel','Employee4.xls');
				}
			}],
			
			onAfterEdit : function (rowIndex,rowData,changs){
				var insert = $("#empdatagrid").datagrid('getChanges','inserted');
				var updata = $('#empdatagrid').datagrid('getChanges','updated');
				var url = '';
				var message = '';
				
				if (insert.length > 0) {
					url = '/Employee/save';
					message = '创建员工成功';
				}
				if(updata.length >0){
					url = '/Employee/updata';
					message = '更新员工信息成功';
					rowData.createtime = new Date();
					rowData.modifytime = new Date();
				}

				$.post(url,rowData,function(data){
					if (data.status == 200) {
						$("#empdatagrid").datagrid('acceptChanges');
						$.messager.alert('提示',message);
						
					}else{
						$("#empdatagrid").datagrid('rejectChanges');
						$.messager.alert('提示',data.msg);
						
					}
					$("#empdatagrid").datagrid('load');
					$("#empdatagrid").datagrid('unselectAll');
					currentEditRow = undefined;
				})
			}										
		});
		$('.datagrid-header div').css('textAlign', 'center');
	});

   //多条件组合查找
	employeeSearch = function(){
		$('#empdatagrid').datagrid('load',serializeFormToObject($('#employee_search').form()));
	}

	//清空查询条件查询
	cleanSearch = function(){
		$('#employee_search').find('input').val('');
		$('#empdatagrid').datagrid('load',{});
	}

	//把form表单中的信息封装成对象
	serializeFormToObject = function(form){
		var obj = {};
		$.each(form.serializeArray(),function(index){
			if (obj[this['name']]) {
				obj[this['name']] = obj[this['name']]+","+this['value'];
			}else{
				obj[this['name']] = this['value'];
			}
		});
		return obj;
	}
	//扩展EasyUi的datagrid的Editorsrrrrr
	$.extend($.fn.datagrid.defaults.editors,{
		datetimebox:{
			init:function(container,options){
				var editor = $('<input />').appendTo(container);
				options.editable = false;
				editor.datetimebox(options);
				return editor;
			},
			getValue:function(target){
				return $(target).datetimebox('getValue');
			},
			setValue:function(target,value){
				$(target).datetimebox('setValue',value);
			},
			resize:function(target,width){
				$(target).datetimebox('resize',width);
			},
			destroy:function(target){
				$(target).datetimebox('destroy');
			}
		}
	});
	
</script>

