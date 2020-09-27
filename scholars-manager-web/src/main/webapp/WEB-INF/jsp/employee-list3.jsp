<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="cc" class="easyui-layout" border='false' fit='true'>
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
    <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div>
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>
</div>

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
				/*{field:'code',title:'Code',width:100},*/
				{field:'ck',checkbox:true},
				{field:'id',width:120,title:'序号',sortable:true},
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

