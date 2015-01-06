<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/WEB-INF/view/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/main.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/user_login.css">
  </head>
  
  <body id="userlogin_body">
    	<form id="loginForm" action="${ctx }/admin/toLogin.do" method="post">
    	<div></div>
		<div id=user_login>
			<dl>
				<dd id=user_top>
					<ul>
						<li class=user_top_l></li>
						<li class=user_top_c></li>
						<li class=user_top_r></li>
					</ul>
				<dd id=user_main>
					<ul>
						<li class=user_main_l></li>
						<li class=user_main_c>
							<div class=user_main_box>
								<ul>
									<li class=user_main_text>
										用户名：
									</li>
									<li class=user_main_input>
										<input class="txtusernamecssclass easyui-validatebox"  data-options="required:true,validType:'email',missingMessage:'邮箱不能为空.',invalidMessage:'邮箱格式不正确'" name="email"  value="admin@qq.com" maxlength="20">
									</li>
								</ul>
								<ul>
									<li class=user_main_text>
										密 码：
									</li>
									<li class=user_main_input>
										<input class="txtpasswordcssclass easyui-validatebox"   data-options="required:true,missingMessage:'密码不能为空.'" type="password" name="pwd" value="123456">
									</li>
								</ul>
								<!-- <ul>
									<li class=user_main_text>
										验证码：
									</li>
									<li class=user_main_input>
										<img class="vc-pic" width="65" height="23" title="点击刷新验证码" src="ImageServlet?time=new Date().getTime()">
										<input class="vc-text easyui-validatebox" data-options="required:true,missingMessage:'验证码不能为空.'" maxlength="4" type="text" name="verifyCode">
									</li>
								</ul> -->
								<ul style="color: red;font-size: medium;">
									 <c:out value="${msg }"></c:out> 
								</ul>
							</div>
						</li>
						<li class=user_main_r>
							<input class="ibtnentercssclass"
								style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px"
								type=image src="${ctx }/resources/images/login/user_botton.gif">
						</li>
					</ul>
			</dl>
		</div>
		<div></div>
    	</form>
  </body>
</html>