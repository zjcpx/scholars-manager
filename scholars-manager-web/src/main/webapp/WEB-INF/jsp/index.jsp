<%@page import="com.alibaba.druid.sql.visitor.functions.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习积分管理平台</title>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src='js/datagrid-export.js'></script>
<script type="text/javascript" src="js/taotao.js"></script>
<%
	String easyuiThemeName = "default";
	Cookie cookies[] = request.getCookies();
	if(cookies != null && cookies.length >0){
		for(int i = 0; i < cookies.length; i++){
			if(cookies[i].getName().equals("easyuiThemeName")){
				easyuiThemeName = cookies[i].getValue();
				break;
			}
			
		}
	}
%>
<link id = "easyuiTheme" rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/<%=easyuiThemeName %>/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />

<script type="text/javascript" src="js/common.js"></script>

</head>
<body class="easyui-layout">
	<!-- 系统配置 -->
	<div id = "controlPanel" data-options="region:'north',title:'',split:false" style="height:100px;">
		<div id = "setCentor">
			<!-- 更换皮肤 -->
			<div id = 'skin' class = 'controlUnit'>
				<span id = 'changeTheme'>更换皮肤</span>
				<div id="mm" class="easyui-menu" style="width:120px;">
					<div onclick="changeTheme('black');">黑色</div>
					<div onclick='changeTheme("bootstrap");'>bootstrap</div>
					<div onclick='changeTheme("default")'>默认</div>
					<div onclick='changeTheme("gray")'>灰色</div>
					<div>   
				        <span>麦德龙</span>   
				        <div style="width:150px;">   
				            <div onclick='changeTheme("metro-blue")'>麦德龙-蓝</div>
				            <div onclick='changeTheme("metro-gray")'>麦德龙-灰</div>
							<div onclick='changeTheme("metro-green")'>麦德龙-绿</div>
							<div onclick='changeTheme("metro-orange")'>麦德龙-桔</div>
							<div onclick='changeTheme("metro-red")'>麦德龙-红</div>  
				        </div>   
				    </div>
				    <div>   
				        <span>UI</span>   
				        <div style="width:150px;">   
				            <div onclick='changeTheme("ui-cupertino")'>ui-cupertino</div>
				            <div onclick='changeTheme("ui-dark-hive")'>ui-dark-hive</div>
							<div onclick='changeTheme("ui-pepper-grinder")'>ui-pepper-grinder</div>
							<div onclick='changeTheme("ui-sunny")'>ui-sunny</div>
				        </div>   
				    </div>    
				</div>  
			</div>
			<!-- 个人 -->
			<div id = "person"  class = 'controlUnit'>
				<span id="changePassword" onclick="showPersonnalMenu();">个人中心</span>
			</div>
		</div>
	</div>
	<!--  管理菜单 -->
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>员工管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'employee-list'}">员工列表</li>
	         		<li data-options="attributes:{'url':'role-list'}">角色列表</li>
	         		<li data-options="attributes:{'url':'department-list'}">部门列表</li>
	         		<li data-options="attributes:{'url':'position-list'}">职位列表</li>
	         		<li data-options="attributes:{'url':'admin-list'}">管理员列表</li>
	         		<li data-options="attributes:{'url':'adminType-list'}">授权类型列表</li>
	         	</ul>
         	</li>
         	<li>
         		<span>公告管理</span>
         		<ul>
	         		
	         		<li data-options="attributes:{'url':'anno-list'}">公告列表</li>
	         		<li data-options="attributes:{'url':'annoreadRec-list'}">阅读记录</li>
	         		<li data-options="attributes:{'url':'annoCommonts-list'}">评论列表</li>
	         		<li data-options="attributes:{'url':'annoQa-list'}">问题列表</li>
	         		<li data-options="attributes:{'url':'annoType-list'}">公告类型列表</li>
	         	</ul>
         	</li>
         	<li>
         		<span>职位管理</span>
         		<ul>
	         		
	         		<li data-options="attributes:{'url':'position-list'}">职位列表</li>
	         		
	         	</ul>
         	</li>
         	<li>
         		<span>学习资料管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'material-list'}">学习资料列表</li>
	         		<li data-options="attributes:{'url':'materialtype-list'}">学习资料类型列表</li>
	         	</ul>
         	</li>
         	<li>
         		<span>课程管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'courses-list'}">课程明细</li>
	         		<li data-options="attributes:{'url':'coursesType-list'}">课程类型明细</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <!-- 详细内容 -->
    <div data-options="region:'center',title:''" >
    	<div id="tabs" class="easyui-tabs" border = "false" fit='true'>

		</div>
    </div>
    <!-- 登录窗口 -->
    <div id="loginWindow" class="easyui-window" title="登陆窗口" style="width:300px;height:200px;padding: 30px;" data-options="modal:true,closable:false,resizable:false,maximizable:false,collapsible:false,minimizable:false">
    	<form action="" method="post" id="login">
    		<div style="margin-top: 10px">   
		        <label for="name">用户名:</label>   
				<input class="easyui-validatebox" type="text" name="empno" id="loginName" data-options="required:true" />   
		    </div>   
		    <div style="margin-top: 10px">   
				<label for="password">密&nbsp;&nbsp;&nbsp;码:</label>   
				<input class="easyui-validatebox" type="password" name="password" id="loginPassword" data-options="required:true" />   
		    </div>   
    	</form>
    	<div style="margin-left:80px;margin-top:15px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
		</div>
    </div>
    !-- 修改密码窗口 -->
    <div id="changePasswordWindow" class="easyui-window" title="修改密码" style="width:350px;height:250px;padding: 30px;" data-options="modal:true,resizable:false,maximizable:false,collapsible:false,minimizable:false">
    	<form action="" method="post" id="changepassord">
    		<div style="margin-top: 10px">   
		        <label style="display:inline-block;width:70px;text-align: right;" for="name">原密码:</label>   
				<input class="easyui-validatebox" type="password" name="originPassword" id="originPassword" data-options="required:true" />   
		    </div>   
		    <div style="margin-top: 10px">   
				<label style="display:inline-block;width:70px;text-align: right;" for="password">新密码:</label>   
				<input class="easyui-validatebox" type="password" name="newpassword" id="newpassword" data-options="required:true" />   
		    </div> 
		    <div style="margin-top: 10px">   
				<label style="display:inline-block;width:70px;text-align: right;" for="password">确认新密码:</label>   
				<input class="easyui-validatebox" type="password" name="confirmpassword" id="confirmpassword" data-options="required:true" />   
		    </div>     
    	</form>
    	<div style="margin-left:80px;margin-top:15px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitChangeForm()">提交</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
		</div>
    </div>
    <!-- 个人中心菜单 -->
    <div id="personnalMenu" class="easyui-menu" style="width:120px;">   
	    <div onclick="changeaccount()">更换账号</div>   
	      
	    <div onclick="changepassword()">更换密码</div>   
	</div>  

    
<script type="text/javascript">
$(function(){
	//判断是否已经登录
	var currName = getCookie("loginName");
	if(currName){
		$("#loginWindow").window('close');
	}
	//弹出登陆窗口
	$("#changePasswordWindow").window('close');

	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localHostPath = curWwwPath.substring(0,pos);
	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	//系统配置部分的所有span标签获得鼠标后都变成手指形状
	$('.controlUnit span').mouseover(function(){
		this.style.cursor = 'pointer';
		
	});

	
	//控制修改皮肤的显示位置
	$('#changeTheme').click(function(e){
		var X = $('#changeTheme').offset().top;
		var Y = $('#changeTheme').offset().left;
		$('#mm').menu('show', {    
			  left: Y,    
			  top: X+16    
			});  
	});
	//系统控制菜单
	$('#menu').tree({
		onClick: function(node){
			
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				
				if(tab){
					tabs.tabs("select",node.text);
					
				}else{
				
					tabs.tabs('add',{
					    title:node.text,
					    
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#login').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		$.post("/Authorize/login",{"empNO":$("#loginName").val(),"password":$("#loginPassword").val()}, function(data){
			if(data.status == 200){
				$.messager.alert('提示','登陆成功!');
				//console.log($("#loginName").val());
				setCookie("loginName",$("#loginName").val());
				$("#loginWindow").window('close');	
			}else{
				$.messager.alert("提示",data.msg);
			}

		});
	}
	
	function clearForm(){
		$('#login').form('reset');
		itemAddEditor.html('');
	}

	function showPersonnalMenu(){
		var X = $('#changePassword').offset().top;
		var Y = $('#changePassword').offset().left;
		$('#personnalMenu').menu('show', {    
			  left: Y,    
			  top: X+16    
			});  
	}

	function changepassword(){
		$("#changePasswordWindow").window("open");
	}

	function changeaccount(){
		$("#loginWindow").window("open");
	}

	function submitChangeForm(){
		if(!$("#changepassord").form("validate")){
			$.messager.alert("提示","还有必要的信息未填写");
			return;
		}else{
			if (!($("#newpassword")[0].value == $("#confirmpassword")[0].value)) {
				$.messager.alert("提示","新密码不相同");
				return;
			}
		}
		$.post("/Authorize/changePassword",{"empNo":getCookie("loginName"),"originpassword":$("#originPassword").val(),"newPassword":$("#confirmpassword").val()},function(data){
			if (data.status == 200) {
				$("#changePasswordWindow").window("close");
				$.messager.alert("提示","密码修改成功");
			}else{
				$.messager.alert("提示",data.msg);
			}
		})
	}
</script>
</body>
</html>