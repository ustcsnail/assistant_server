<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"/>
<title>园区助手后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="js/jquery.js"></script>
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script>

	(function($){
		$(window).load(function(){
			
			$("a[rel='load-content']").click(function(e){
				e.preventDefault();
				var url=$(this).attr("href");
				$.get(url,function(data){
					$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
					//scroll-to appended content 
					$(".content").mCustomScrollbar("scrollTo","h2:last");
				});
			});
			
			$(".content").delegate("a[href='top']","click",function(e){
				e.preventDefault();
				$(".content").mCustomScrollbar("scrollTo",$(this).attr("href"));
			});
			
		});
	})(jQuery);
</script>
</head>
<body>
<!--header-->
<header>
 <h1><img src="images/admin_logo.png"/></h1>
 <ul class="rt_nav">
  <li><a href="main.jsp" target="_blank" class="website_icon">站点首页</a></li>
  <li><a href="index.jsp" class="quit_icon">安全退出</a></li>
 </ul>
</header>
<!--aside nav-->
<!--aside nav-->
<aside class="lt_aside_nav content mCustomScrollbar">
 <h2><a href="main.jsp">起始页</a></h2>
 <ul>
  <li>
   <dl>
    <dt>科研动态管理</dt>
    <!--当前链接则添加class:active-->
    <dd><a href="ResearchMain.jsp" >科研动态列表</a></dd>
    <dd><a href="publisharticle.jsp" >发布科研动态</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>美食推荐管理</dt>
    <dd><a href="RestaurantMain.jsp">推荐餐馆列表</a></dd>
    <dd><a href="publishrestaurant.jsp">添加推荐餐馆</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>生活指南管理</dt>
    <dd><a href="directorMain.jsp">生活指南列表</a></dd>
    <dd><a href="publishdirectory.jsp">添加生活指南</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>用户管理</dt>
    <dd><a href="BackUser?operate=query">用户列表</a></dd>
   </dl>
  </li>
  <li>
   <p class="btm_infor">© USTC 版权所有</p>
  </li>
 </ul>
</aside>


</body>
</html>
