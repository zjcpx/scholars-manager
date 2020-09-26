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
	<div region = 'center' border = 'false' fit='true'>
		<table id = 'annoTypeDatagrid' style="height: 100%"></table>

	</div>
</div>

<script type="text/javascript" charset="utf-8">
	function getSelectionsIds(){
		var ids = $("#annoTypeDatagrid").datagrid("getSelections");
		return ids;
	}

	$(function(){
		var currentEditRow = undefined;
		$("#annoTypeDatagrid").datagrid({
			title:'公告类型明细',
			url:'/annoType/list',
			pagination:true,
			pageSize:30,
			pageList:[30,60,90],
			fit:true,
			fitColumns:true,
			nowarp:false,
			border:true,
			idfield:'id',
			sortName:'typename',
			sortOrder:'desc',
			columns:[[
				{field:'id',width:100,title:'序号',sortable:true,checkbox:true},
				{field:'typename',width:160,title:'公告类型',sortable:true,editor:{
					type:'validatebox',
					options:{
						required:true,
						validType:'length[1,20]',
						missingMessage:'请输入类型名称',
						}
					},

				},
				{field:'createtime',width:80,title:'发布时间',sortable:true,formatter:TAOTAO.formatDateTime
				},
				{field:'modifytime',width:80,title:'修改时间',sortable:true,formatter:TAOTAO.formatDateTime
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',

				handler:function(){
					if (currentEditRow != undefined) {
						$("#annoTypeDatagrid").datagrid('endEdit',currentEditRow)
					}else{						
						$("#annoTypeDatagrid").datagrid('insertRow',{
							index:0,
							row:{
							}
						});
						$("#annoTypeDatagrid").datagrid('beginEdit',0);
						currentEditRow = 0;
					}					
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var ids = getSelectionsIds();
					if(ids.length == 0){
		        		$.messager.alert('提示','未选中类型!');
		        		return ;
		        	}
					$.messager.confirm('确认','确定删除ID为 "'+ids[0].typename+'" 的类型吗？',function(r){
		        	    if (r){
		        	    	var params = {"ids":ids};
		        	    	console.log(ids);
		                	$.post("/annoType/delete",params, function(data){
		            			if(data.status == 200){
		            				$("#annoTypeDatagrid").datagrid("load");
		            				$.messager.alert('提示','删除商品成功!',undefined,function(){
		            					
		            				});
		            			}
		            		});
		        	    }
		        	});

				}
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					var ids = getSelectionsIds();
					if (ids.length == 1) {
						var index = $("#annoTypeDatagrid").datagrid("getRowIndex",ids[0]);
						if (currentEditRow != undefined) {
							$.messager.confirm('确认','是否要保存当前的修改？',function(r){
								if(r){
									$("#annoTypeDatagrid").datagrid('endEdit',currentEditRow);
								}
								$("#annoTypeDatagrid").datagrid('rejectChanges');
							})
							
							$("#annoTypeDatagrid").datagrid('beginEdit',index);
						}
						if (currentEditRow == undefined) {				
							$("#annoTypeDatagrid").datagrid('beginEdit',index);
							currentEditRow = index;
						}			
					}else{
						if(ids.length > 1){
							$.messager.alert('提示','一次只能编辑一个公告类型','error');
						}else{
							$.messager.alert('提示','请选择一个公告类型','error');
						}
						

					}
				}
			},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					$("#annoTypeDatagrid").datagrid('endEdit',currentEditRow);
				}
			},'-',{
				text:'放弃编辑',
				iconCls:'icon-redo',
				handler:function(){
					currentEditRow = undefined;
					$("#annoTypeDatagrid").datagrid("rejectChanges");
					$("#annoTypeDatagrid").datagrid("unselectAll");
				}
			},'-'],
			onAfterEdit : function (rowIndex,rowData,changs){
				var inserts = $("#annoTypeDatagrid").datagrid('getChanges','inserted');
				var updated = $('#annoTypeDatagrid').datagrid('getChanges','updated');
				var url;
				if (inserts.length > 0) {
					url = "/annoType/save";
				}
				if (updated.length > 0) {
					url = "/annoType/update";
				}
			
				$.ajax({
					type : 'POST',
					url : url,
					// data : parms,
					async : true,
			        contentType: "application/json; charset=utf-8",
			        data:JSON.stringify(rowData),
			        dataType : "json",
					success:function(data){
						if(data.status == 200){
							$("#annoTypeDatagrid").datagrid("acceptChanges");
							$("#annoTypeDatagrid").datagrid("load");
							$.messager.alert('提示',data.msg);
						}else{
							$("#annoTypeDatagrid").datagrid("rejectChanges");
							$.messager.alert('提示', data.msg);
							
						}
						currentEditRow = undefined;
						$('#annoTypeDatagrid').datagrid('unselectAll');	
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

