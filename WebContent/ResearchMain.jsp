<%@ page language="java" import="java.util.*" import="entity.*" contentType="text/html; charset=utf-8"
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

<section class="rt_wrap content mCustomScrollbar">

  <div class="rt_content">

    <div class="page_title">
       <h2 class="fl">科研动态列表</h2>
       <a href="publisharticle.jsp" class="fr top_rt_btn add_icon">添加动态</a>
    </div>
    <form action="ResearchServlet" method="post">
       <section class="mtb">
                              学科门类：&nbsp
           <select name="subject" class="select">
              <option value="1" >  理学  </option>
              <option value="2" >  工学  </option>
              <option value="3" >  经济学  </option>
              <option value="4" >  医学  </option>
              <option value="5" >  社会学  </option>
              <option value="6" >  其他  </option>
           </select>&nbsp&nbsp
                              
       <input type="submit" value="查询" class="group_btn"/>
     </section>     
    </form>
    <table class="table">
       <tr>
        <th>ID</th>
        <th>题目</th>
        <th>单位</th>
        <th>作者</th>
        <th>时间</th>
        <th>操作</th>
       </tr>
   
              <%List<ResearchEntity> list=(List<ResearchEntity>)request.getAttribute("list"); %>
              <%if(list!=null) {%>
            	  <%for(ResearchEntity e:list){%>
            	   <tr>
                     <td class="center"><%=e.getId() %></td>
                     <td class="center"><%=e.getTitle() %></td>
                     <td class="center"><%=e.getUniversity() %></td>
                     <td class="center"><%=e.getAuthor() %></td>
                     <td class="center"><%=e.getDate() %></td>
                     <td class="center">
                     <a href="BackResearch?id=<%=e.getId()%>&operate=query" title="编辑" class="link_icon">&#101;</a>
                     <a href="BackResearch?id=<%=e.getId()%>&operate=delete" title="删除" class="link_icon">&#100;</a>
                  </td>
                  </tr>
                  <% }%>
              <%}%>
   </table>
    
  </div>

</section>

</body>
</html>