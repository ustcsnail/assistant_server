<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //验证码
  createCode();
  //测试提交，对接程序删除即可
  $(".submit_btn").click(function(){
	  location.href="index.html";
	  });
});
</script>
</head>
<body>
 <form action="AdminServlet" method="post">
<dl class="admin_login">
 <dt>
  <strong>园区助手后台管理系统</strong>
  <em>Management System</em>
 </dt>

 <dd class="user_icon">
  <input name="username" type="text" placeholder="账号" class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input name ="passwd" type="password" placeholder="密码" class="login_txtbx"/>
 </dd>
 
 <dd>
  <input type="submit" value="立即登录" class="submit_btn"/>
 </dd>

 <dd>
 
 </dd>
</dl>
 </form>
</body>
</html>
    