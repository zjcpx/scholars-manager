<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- <div class="easyui-tabs" fit = 'true' border = "false">
	<div title="员工列表" border = "false">
		<div id="demo" style="width: 100px;height: 50px;background-color: #ff0">
			
		</div> -->
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
								<input style="width: 180px" name='createtime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
							</td>
							<th style="width: 70px;text-align: right;">修改时间</th>
							<td>
								<input style="width: 180px" name='modfytime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
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
				<table id = 'datagrid'></table>
			</div>
		</div>
	<!-- </div>
</div> -->
<script type="text/javascript" charset="utf-8">
	$(function(){
		console.log('I am here!');
		var currentEditRow = undefined;
		$("#datagrid").datagrid({
			title:'员工列表',
			url:'/employee/list',
			pagination:true,
			pageSize:30,
			pageList:[30,60,90],
			fit:true,
			fitColumns:true,
			nowarp:false,
			border:false,
			idfield:'id',
			sortName:'score',
			sortOrder:'desc',
			columns:[[
				
				{field:'id',width:120,title:'序号',sortable:true,checkbox:true},
				{field:'no',width:60,title:'员工编号',sortable:true,editor:{
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
					
				}},
				{field:'name',width:80,title:'姓名',sortable:true,editor:{
					type:'validatebox',
					options:{
						required:true,
						validType:'length[1,5]',
						missingMessage:'请输入员工姓名',
						}
				}},
				{field:'tel',width:100,title:'电话',editor:{
					type:'validatebox',
					options:{
						required:true,
					
						missingMessage:'请输入联系电话',
						
					}
				}},
				{field:'email',width:200,title:'电子邮箱',editor:{
					type:'validatebox',
					options:{
						required:true,
						validType:'email',
						missingMessage:'请输入邮箱',
						invalidMessage:'您输入的邮箱不合规，请修改'
					}
				}},
				{field:'qq',width:100,title:'QQ号码',editor:{
					type:'validatebox',
					options:{required:true}
				}},
				{field:'dep',width:100,title:'所属部门',sortable:true,editor:{
					type:'combobox',
					options:{
						required:true,
						editable:false,
						url:"/dept/list",
						valueField:'depName',
	        			textField:'depName'
					}
				}},
				{field:'position',width:50,title:'职位',sortable:true,editor:{
					type:'combobox',
					options:{
						required:true,
						editable:false,
						url:'/role/list2',
				        valueField:'roleName',
				        textField:'roleName'
					}
				}},
				{field:'role',width:50,title:'角色',sortable:true,editor:{
					type:'combobox',
					options:{
						required:true,
						editable:false,
						url:'/position/list2',
				        valueField:'positionname',
				        textField:'positionname'
						
					}
				}},
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
				}}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				
				handler:function(){
					if (currentEditRow != undefined) {
						$("#datagrid").datagrid('endEdit',currentEditRow)
					}else{
						
						$("#datagrid").datagrid('insertRow',{
							index:0,
							row:{

							}
						});
						$("#datagrid").datagrid('beginEdit',0);
						currentEditRow = 0;
					}
					
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){

				}
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){

				}
			},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					$("#datagrid").datagrid('endEdit',currentEditRow);
				}
			}],
			onAfterEdit : function (rowIndex,rowData,changs){
				$.post("/employee/save",rowData, function(data){
					if(data.status == 200){
						
						$("#datagrid").datagrid("reload");
						currentEditRow = undefined;
						$.messager.alert('提示','员工添加成功!');
					}else{
						$.messager.alert('提示', data.msg);
					}
				})
			}										
		});
		$('.datagrid-header div').css('textAlign', 'center');
	})
	employeeSearch = function(){
		// console.log(serializeFormToObject($('#employee_search').form()));
		$('#datagrid').datagrid('load',serializeFormToObject($('#employee_search')));
	}

	cleanSearch = function(){
		$('#employee_search').find('input').val('');
		$('#datagrid').datagrid('load',{});
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

