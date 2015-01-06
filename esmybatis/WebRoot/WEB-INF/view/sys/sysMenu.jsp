<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sysMenu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/WEB-INF/view/resource.jsp" %>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(function() {
			$("#menuList").datagrid({
				url : '${ctx}/admin/sysMenu/list.do',
				rownumbers : true,
				singleSelect : true,
				pagination : true,
				border : false,
				pageNumber : 1,
				pageSize : 10,
				pageList : [10,20,30],
				columns : [[
				            {field : 'id',checkbox:true},
				            {field:'name',title:'名称',width:120},
							{field:'rank',title:'排序',align:'right',width:80},
							{field:'url',title:'URL',width:220},
							{field:'createTime',title:'添加时间',width:140},
							{field:'updateTime',title:'修改时间',width:140},
							{field:'handle',title:'操作',align:'center',width:400,formatter:function(value,row,index) {
								var deletebtn = "<a href='javascript:fn_delete();' class='easyui-linkbutton' data-options='iconCls:'icon-remove''>删除</a>";
								return deletebtn;
							}}
				 ]],
				toolbar : '#toolbar'
			});
			
		});
		
	function fn_delete() {
	
	}
	</script>

  </head>
  
  <body>
  	 <!-- Search panel start -->
	<div class="easyui-panel" style="padding:0.5px;" id="toolbar" data-options="doSize:false">
		<div class="easyui-panel" title="搜索条件"	data-options="iconCls:'icon-search',collapsible:true">
		<form id="searchForm">
	 	 	<input class="hidden" id='search_parentId' name="parentId">
	 	 	<p class="ui-fields">
	            <label class="ui-label">Name:</label> 
	            <input name="name" class="easyui-box ui-text" style="width:100px;">
	        </p>
	        <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
      </form>  
		</div>
	</div>
     <!--  Search panel end -->
     	<table id="menuList"></table>
  </body>
</html>
