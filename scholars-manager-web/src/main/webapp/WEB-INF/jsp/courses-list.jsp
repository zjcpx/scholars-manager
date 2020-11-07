<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="easyui-layout" border='false' fit='true'>
	<!-- 过滤窗口 -->
	<div region = 'north' border = 'false' title="过滤" style='height: 110px;overflow: hidden;'>
		<form id="course_search">
			<table class="tableForm coursedatagrid-toolbar" style="width: 100%;height: 100%">
				<tr>
					<th style="width: 70px;text-align: right;">课程名称</th>
					<td><input style="width: 180px" type="text" name="coursename" id="coursename" class="easyui-textbox"></td>
					<th style="width: 70px;text-align: right;">必修人员</th>
					<td><input style="width: 180px" type="text" name="studyperson" id="studyperson" class="easyui-textbox"></td>
					<th style="width: 70px;text-align: right;">课程学分</th>
					<td><input style="width: 180px" name="scores" id="scores" class="easyui-numberbox"></td>
					<th style="width: 80px;text-align: right;">课程作者</th>
					<td><input style="width: 180px" type="text" class="easyui-textbox" name="author" id="author" placehold="0"></td>
				</tr>
				
				<tr>
					<th style="width: 80px;text-align: right;">课程类型</th>
					<td><input style="width: 180px" type="text" class="easyui-combobox" name="coursetype" id="coursetype" placehold="0"></td>
					<th style="width: 70px;text-align: right;">截止时间</th>
					<td>
						<input id = "dateline" style="width: 180px" name='dateline' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
					<th style="width: 70px;text-align: right;">创建时间</th>
					<td>
						<input id = "createtime" style="width: 180px" name='createtime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
					
					<th style="width: 70px;text-align: right;">修改时间</th>
					<td>
						<input id = "modifytime" style="width: 180px" name='modifytime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
				</tr>
				<tr>	
					<th >
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'courseSearch();'>根据条件查询</a>
					</th>
					<th>
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'annoRest()' >清空查询条件</a>
					</th>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格 -->
	<div region = 'center' border = 'false' >
		<table id = 'coursedatagrid' style="height: 100%">
		</table>
	</div>
</div>
<!-- 右键菜单 -->
<div id = "annoOpMenu" class="easyui-menu" style="width:80px;display:none;">
	<div onclick = 'addCourseType()' iconCls = 'icon-add'>新建</div>
	<div onclick = 'editCourse()' iconCls = 'icon-edit'>编辑</div>
	<div onclick = 'removeCourse()' iconCls = 'icon-remove'>删除</div>
</div>
<!-- 导入Excel对话框 -->
<div id="inputDialog" title ="">
	<form id="inputForm"  method="post" enctype="multipart/form-data" style=" margin-left:30px" >
		<input type="file" name="file" id="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" style="height: 25px;width:200px;margin-top:15px;" class="easyui-filebox">
	</form>
</div>

<script type="text/javascript" charset="utf-8">

	$("#coursetype").combobox({
		editable:false,
		url:'/CourseType/list',
		valueField:'typename',
		textField:'typename'

	})

	//确认是否保存修改
	confirm = function(Object,editRow){
		$.messager.confirm('确认','你要保存当前的修改吗？',function(r){    
		    if (r){ 
		    	
		    	Object.datagrid('endEdit',editRow); 
		    }else{
		    	Object.datagrid('unselectAll');
		    	Object.datagrid('rejectChanges');
		    	editRow = undefined;
		    }    
		});  
	}

	//获取选择的课程列表
	function getSelections(){
		var ids = $('#coursedatagrid').datagrid("getSelections");
		return ids;
	}

	//根据检索条件查找匹配的课程内容
	courseSearch = function (){
		
		$('#coursedatagrid').datagrid('load',serializeToObject($('#course_search').form()));
	}
		
	//清空查询条件
	annoRest = function(){
		//把过滤条件中的内容置空
		$('#course_search ').form().find("input").val("");
		$('#coursedatagrid').datagrid('load',{});
	}
		
	//增加课程
	addCourseType = function(){
		var tabs = $("#tabs");
		var tab = tabs.tabs("getTab",'新增课程');
		if(tab){
			tabs.tabs("select",'新增课程');
		}else{
	        tabs.tabs('add',{
	            title:'新增课程',
	            href: 'course-add',
	            closable:true,
	            bodyCls:"content"
	        });
	    }
	}

	//删除课程
	removeCourse = function(){

		var id = getSelections();
		//是否选择了待删除的课程
		if(id.length == 0){
			$.messager.alert('提示',"请选择需要删除的课程");					
			return;
		}else{
			var ids = [];
			var annoTitle = [];
			for (var i in id) {
				ids.push(id[i].id);
				annoTitle.push(id[i].coursename);
			}
			var idString = ids.join(",");
			annoTitle = annoTitle.join(",");
			$.messager.confirm('提示','确定要删除标题为"'+annoTitle+'"的课程?',function(r){
				if (r) {
					$.post('/Course/delete',{ids:idString},function(data){
						if (data.status == 200) {
							$.messager.alert('提示','课程删除成功');
							$("#coursedatagrid").datagrid("load");
							$("#coursedatagrid").datagrid("acceptChanges");
							currentEditRow = undefined;
						}else{
							$.messager.alert('提示','课程删除失败');
						}
					})
				}
				$("#coursedatagrid").datagrid("rejectChanges");
			})
		}
	} 

	//编辑课程
	editCourse = function(){
		var id = getSelections();
		if(id.length == 1){
			var tabs = $("#tabs");
			var tab = tabs.tabs("getTab",'编辑课程');
			if(tab){
				tabs.tabs("select",'编辑课程');
			}else{
	            tabs.tabs('add',{
	                title:'编辑课程',
	                href: 'course-edit',
	                closable:true,
	                bodyCls:"content"
	            });
	        }
		}else{
			$.messager.alert("提示","一次只能编辑一门课程");
		};
	}

	//初始化课程列表
	$(function(){
		$("#coursedatagrid").datagrid({
			title:'课程明细',
			url:'/Course/grid',
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
				{field:'id',width:80,title:'序号',checkbox:true},
				{field:'coursename',width:100,title:'课程名称',sortable:true},
				{field:'coursecontents',width:100,title:'课程内容'},
				{field:'coursetype',width:100,title:'课程类型'},
				{field:'scores',width:30,title:'课程学分',sortable:true},
				{field:'studyperson',width:60,title:'必修人员'},
				{field:'author',width:60,title:'课程作者',sortable:true},
				{field:'dateline',width:70,title:'截止时间',sortable:true,formatter:function(param){
					return new Date(param).format("yyyy/MM/dd hh:mm:ss");}},
				{field:'createtime',width:70,title:'初建时间',sortable:true,formatter:function(param){
					return new Date(param).format("yyyy/MM/dd hh:mm:ss");
				}},
				{field:'modifytime',width:70,title:'修改时间',sortable:true,formatter:function(param){
					return new Date(param).format("yyyy/MM/dd hh:mm:ss");
				}},
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					//新增tab方式实现增加课程
					addCourseType();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					removeCourse();
				}
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					//tab方式实现修改功能
					editCourse();
					
				}
			},'-',{
				text:'导出Excel',
				iconCls:'icon-edit',
				handler:function(){

					$("#coursedatagrid").datagrid('toExcel','Courses.xls');
				}
			}],
			onAfterEdit : function (rowIndex,rowData,changs){
				if(!$.isEmptyObject(changs)){
					$.messager.confirm('请确认','是否要保存修改的内容？',function(r){
						if (r) {
							rowData.createtime = new Date(rowData.createtime);
							rowData.modifytime = new Date(rowData.modifytime);
							$.post('/anno/update',rowData,function(data){
								if(data.status == 200){
									$.messager.alert('提示','课程更新成功!');
									$("#coursedatagrid").datagrid("load");
								};
							});
						}else{
							$("#coursedatagrid").datagrid('rejectChanges');
						}
					})
				}
				currEditRow = undefined;
				$("#coursedatagrid").datagrid('unselectAll');
			},
			
			onDblClickRow:function(index, row){
				if(currEditRow != undefined){
					$("#coursedatagrid").datagrid('endEdit',currEditRow);
				}else{
					$("#coursedatagrid").datagrid('beginEdit',index)
					$("#coursedatagrid").datagrid('selectRow',index)
					currEditRow = index;
				}
				
			}, 
			
			onClickRow:function(index,row){
				if(currEditRow != undefined){
					$("#coursedatagrid").datagrid('endEdit',currEditRow);
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

