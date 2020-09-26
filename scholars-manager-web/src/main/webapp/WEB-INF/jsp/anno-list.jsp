<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="easyui-layout" border='false' fit='true'>
	<!-- 过滤窗口 -->
	<div region = 'north' border = 'false' title="过滤" style='height: 110px;overflow: hidden;'>
		<form id="anno_search">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%">
				<input style="width: 180px" type="hidden" name="content" id="content" >
				<input style="width: 180px" type="hidden" name="attachments" id="attachments" >
				<tr>
					<th style="width: 70px;text-align: right;">公告编号</th>
					<td><input style="width: 180px" type="text" name="id" id="id" class="easyui-numberbox"></td>
					<th style="width: 70px;text-align: right;">公告标题</th>
					<td><input style="width: 180px" type="text" name="title" id="title" class="easyui-textbox"></td>
					<th style="width: 70px;text-align: right;">公告类型</th>
					<td><input style="width: 180px" name="type" id="type" ></td>
				</tr>
				
				<tr>
					<th style="width: 80px;text-align: right;">公告积分</th>
					<td><input style="width: 180px" type="text" class="easyui-numberbox" name="score" id="score" placehold="0"></td>
					<th style="width: 70px;text-align: right;">创建时间</th>
					<td>
						<input id = "createtime" style="width: 180px" name='createtime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
					<th style="width: 70px;text-align: right;">修改时间</th>
					<td>
						<input id = "modifytime" style="width: 180px" name='modifytime' class='easyui-datetimebox' style="width: 155px" editable='false'/>
					</td>
					<th >
						<a style="width: 100px" href="javascript:void(0)" class="easyui-linkbutton" onclick = 'annoSearch();'>根据条件查询</a>
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
		<table id = 'datagrid' style="height: 100%">
		</table>
	</div>
</div>
<!-- 右键菜单 -->
<div id = "annoOpMenu" class="easyui-menu" style="width:80px;display:none;">
	<div onclick = 'addAnno()' iconCls = 'icon-add'>新建</div>
	<div onclick = 'editAnno()' iconCls = 'icon-edit'>编辑</div>
	<div onclick = 'removeAnno()' iconCls = 'icon-remove'>删除</div>
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

	//获取选择的公告列表
	function getSelections(){
		var ids = $('#datagrid').datagrid("getSelections");
		return ids;
	}

	//根据检索条件查找匹配的公告内容
	annoSearch = function (){
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
		$('#datagrid').datagrid('load',serializeToObject($('#anno_search').form()));
		//为保持页面显示的友好
		$("#createtime").textbox('setValue',time1);
		$("#modifytime").textbox('setValue',time2);
	}
		
	//清空查询条件
	annoRest = function(){
		//把过滤条件中的内容置空
		$('#anno_search ').form().find("input").val("");
		$('#datagrid').datagrid('load',{});
	}
		
	//增加公告
	addAnno = function(){
		var tabs = $("#tabs");
		var tab = tabs.tabs("getTab",'新增公告');
		if(tab){
			tabs.tabs("select",'新增公告');
		}else{
	        tabs.tabs('add',{
	            title:'新增公告',
	            href: 'anno-add',
	            closable:true,
	            bodyCls:"content"
	        });
	    }
	}

	//删除公告
	removeAnno = function(){

		var id = getSelections();
		//是否选择了待删除的公告
		if(id.length == 0){
			$.messager.alert('提示',"请选择需要删除的公告");					
			return;
		}else{
			var ids = [];
			var annoTitle = [];
			for (var i in id) {
				ids.push(id[i].id);
				annoTitle.push(id[i].title);
			}
			ids = ids.join(",");
			annoTitle = annoTitle.join(",");
			$.messager.confirm('提示','确定要删除标题为"'+annoTitle+'"的公告?',function(r){
				if (r) {
					$.post('/anno/delete',{id:ids},function(data){
						if (data.status == 200) {
							$.messager.alert('提示','公告删除成功');
							$("#datagrid").datagrid("load");
							$("#datagrid").datagrid("acceptChanges");
							currentEditRow = undefined;
						}else{
							$.messager.alert('提示','公告删除失败');
						}
					})
				}
				$("#datagrid").datagrid("rejectChanges");
			})
		}
	} 

	//编辑公告
	editAnno = function(){
		var id = getSelections();
		if(id.length == 1){
			var tabs = $("#tabs");
			var tab = tabs.tabs("getTab",'修改公告');
			if(tab){
				tabs.tabs("select",'修改公告');
			}else{
	            tabs.tabs('add',{
	                title:'编辑公告',
	                href: 'anno-edit',
	                closable:true,
	                bodyCls:"content"
	            });
	        }
		}else{
			$.messager.alert("提示","一次只能修改一个公告");
		};
	}

	//初始化公告列表
	$(function(){
		$("#datagrid").datagrid({
			title:'公告明细',
			url:'anno/annoList2',
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
				{field:'title',width:100,title:'标题',sortable:true,editor:{
						type:'validatebox',
						options:{
							required:true,
						}
					},
					formatter:function(value,rowDate,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},
				{field:'content',width:350,title:'内容',editor:{
					type : 'validatebox',
					options : {
						required:true,
					}
				}},
				{field:'type',width:60,title:'公告类型',sortable:true,editor:{
					type:'combobox',
					options : {
						required:true,
						editable:false,
						url:'/annotype/annoTypeList',
						valueField:'typename',
						textField:'typename'
					}
				}},

				{field:'score',width:30,title:'学习积分',sortable:true,editor:{
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
				{field:'attachments',width:80,title:'附件'},
				{field:'createtime',width:70,title:'初建时间',formatter:function(param){
					return new Date(param).format("yyyy/MM/dd hh:mm:ss");
				}},
				{field:'modifytime',width:70,title:'修改时间',formatter:TAOTAO.formatDateTime},
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					//新增tab方式实现增加公告
					addAnno();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					removeAnno();
				}
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					//tab方式实现修改功能
					editAnno();
					/* var id = getSelections();
					if(id.length == 1){
						var tabs = $("#tabs");
						var tab = tabs.tabs("getTab",'修改公告');
						if(tab){
							tabs.tabs("select",'修改公告');
						}else{
			                tabs.tabs('add',{
			                    title:'编辑公告',
			                    href: 'anno-edit',
			                    closable:true,
			                    bodyCls:"content"
			                });
			            }
					}else{
						$.messager.alert("提示","一次只能修改一个公告");
					}; */				
				}
			},'-',{
				text:'保存修改',
				iconCls:'icon-save',
				handler:function(){
					if(currEditRow != undefined){
						$("#datagrid").datagrid('endEdit',currEditRow);
					}
				}
			},'-',{
				text:'放弃修改',
				iconCls:'icon-cancel',
				handler:function(){
					
				}
			},'-',{
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
			}],
			onAfterEdit : function (rowIndex,rowData,changs){
				if(!$.isEmptyObject(changs)){
					$.messager.confirm('请确认','是否要保存修改的内容？',function(r){
						if (r) {
							rowData.createtime = new Date(rowData.createtime);
							rowData.modifytime = new Date(rowData.modifytime);
							$.post('/anno/update',rowData,function(data){
								if(data.status == 200){
									$.messager.alert('提示','公告更新成功!');
									$("#datagrid").datagrid("load");
								};
							});
						}else{
							$("#datagrid").datagrid('rejectChanges');
						}
					})
				}
				currEditRow = undefined;
				$("#datagrid").datagrid('unselectAll');
			},
			
			onDblClickRow:function(index, row){
				if(currEditRow != undefined){
					$("#datagrid").datagrid('endEdit',currEditRow);
				}else{
					$("#datagrid").datagrid('beginEdit',index)
					$("#datagrid").datagrid('selectRow',index)
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

