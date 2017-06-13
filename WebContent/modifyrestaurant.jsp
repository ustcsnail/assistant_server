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

    <section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">添加餐馆</h2>
      
      </div>
     <section>
     <form action="BackRestaurant" method="post">
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">名称：</span>
        <input name="name" value="<%=request.getAttribute("name")%>" type="text" class="textbox textbox_295" placeholder="餐馆名称..."/>  
           
       </li>
       <li>
        <span class="item_name" style="width:120px;">ID：</span>
        <input name="id" type="text" value="<%=request.getAttribute("id")%>" class="textbox" placeholder="ID..." readonly="readonly"/>  
           
       </li>
       <li>
        <span class="item_name" style="width:120px;">标签：</span>
        <input name="label" value="<%=request.getAttribute("label")%>" type="text" class="textbox" placeholder="餐馆招牌食品..."/>
       </li>
       <li>
        <span class="item_name" style="width:120px;">评分：</span>
        <input name="mark" value="<%=request.getAttribute("mark")%>" type="text" class="textbox" placeholder="星级评分..."/>
       </li>
       <li>
        <span class="item_name" style="width:120px;">均价：</span>
        <input name="price" value="<%=request.getAttribute("price")%>" type="text" class="textbox" placeholder="消费均价..."/>
       </li>
       <li>
        <span class="item_name" style="width:120px;">等级：</span>
         <%String l=(String)request.getAttribute("level"); int level=Integer.parseInt(l);%>
        <select name="level" class="select">
        
         <option value=1 <%if(level==1){ %> selected="selected"<% }%>>平价</option>
         <option value=2 <%if(level==2){ %> selected="selected"<% }%>>优质</option>
         <option value=3 <%if(level==3){ %> selected="selected"<% }%>>奢侈</option>
        </select>
       </li>
       <li>
        <span class="item_name" style="width:120px;">分类：</span>
         <%String t=(String)request.getAttribute("type"); int type=Integer.parseInt(t);%>
        <select name="type" class="select">
         <option value=1 <%if(type==1){ %> selected="selected"<% }%>>中餐</option>
         <option value=2 <%if(type==2){ %> selected="selected"<% }%>>西餐</option>
         <option value=3 <%if(type==3){ %> selected="selected"<% }%>>甜品</option>
         <option value=4 <%if(type==4){ %> selected="selected"<% }%>>快餐</option>   
        </select>      
       </li>
       
       <li>
        <span class="item_name" style="width:120px;">商圈：</span>
       <input name="district" value="<%=request.getAttribute("district")%>" type="text" class="textbox" placeholder="地标商圈..."/>
      
       </li>
       <li>
        <span class="item_name" style="width:120px;">地址：</span>
        <input name="address" value="<%=request.getAttribute("address")%>" type="text" class="textbox  textbox_295" placeholder="地址..."/>
        <span class="item_name" style="width:120px;">&nbsp&nbsp&nbsp</span>
         <input type="submit" value="修改" class="group_btn"/>   
       </li>
       
      </ul>
      </form>
     </section>
   </div>
   </section>

</body>
</html>