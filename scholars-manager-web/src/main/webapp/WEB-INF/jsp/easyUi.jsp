<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘淘商城后台管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	/* .content {
		padding: 10px 10px 10px 10px;
	} */
</style>
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="height:100px;"></div>
    <div data-options="region:'west',title:'管理菜单',split:true" style="width:200px;">
    	<ul id="tt" class="easyui-tree">
		    <li>
		        <span>菜单</span>
		        <ul>
		            <li>
		                <span>系统管理</span>
		                <ul>
		                    <li data-options="attributes:{'url':'employee-list2'}">员工列表</li>
		                    <li ><span>File 12</span></li>
		                    <li><span>File 13</span></li>
		                </ul>
		            </li>
		            <li data-options="attributes:{'url':'employee-list2'}">查询员工</li>
		            <li><span>File 3</span></li>
		        </ul>
		    </li>
		    <li><span>File21</span></li>
		</ul>
    </div>
    <div data-options="region:'center'" style="background:#eee;overflow: hidden;">
    	<div id="tabs" class="easyui-tabs"  border='false' fit='true'>
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		</div>
    </div>
    <script type="text/javascript">
    	$(function(){
    		$('#tt').tree({
    			onClick:function(node){
    				console.log(node.attributes);
    				if($('#tt').tree('isLeaf',node.target)){
    					var tabs = $('#tabs');
    					var tab = tabs.tabs('getTab',node.text);
    					if(tab){
    						tabs.tabs('select',node.text);
    					}else{
    						tabs.tabs('add',{
    							title:node.text,
    							href:node.attributes.url,
    							closable:true,
    							bodyCls:'content'
    						})
    					}
    				}
    			}
    		});
    	});


 //    	.tree({
	// 	onClick: function(node){
	// 		if($('#menu').tree("isLeaf",node.target)){
	// 			var tabs = $("#tabs");
	// 			var tab = tabs.tabs("getTab",node.text);
	// 			if(tab){
	// 				tabs.tabs("select",node.text);
	// 			}else{
	// 				tabs.tabs('add',{
	// 				    title:node.text,
	// 				    href: node.attributes.url,
	// 				    closable:true,
	// 				    bodyCls:"content"
	// 				});
	// 			}
	// 		}
	// 	}
	// });
	</script>
</body>
</html>