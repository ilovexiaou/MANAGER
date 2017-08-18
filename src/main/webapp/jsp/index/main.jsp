<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
 <head>
  <title> 会员管理系统</title>
  <link rel="icon" href="<%=basePath%>img/index/neutronsoft.ico" type="image/x-icon">
    <link rel="shortcut icon" href="<%=basePath%>img/neutronsoft.ico" type="image/x-icon">
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="<%=basePath%>assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
  <link href="<%=basePath%>assets/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="<%=basePath%>assets/css/main-min.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
  <div class="header">
    
      <div class="dl-title">
        <a href="<%=basePath%>reflash.do" title="首页" >
          <span class="lp-title-port"></span><span class="dl-title-text">会员管理系统</span>
        </a> 
      </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${sessionScope.china_name}</span><a href="<%=basePath%>quitLogin.do" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <ul id="J_Nav"  class="nav-list ks-clear"></ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
   </div>
  <script type="text/javascript" src="<%=basePath%>assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>assets/js/bui.js"></script>
  <script type="text/javascript" src="<%=basePath%>assets/js/config.js"></script>
  <script type="text/javascript">
  jQuery.ajax( {  
      type : 'GET', 
      dataType: "text",
      url : '<%=basePath%>getMenu.do',  
       dataType : 'json',   
      success : function(data) {
          for(var i = 0; i<data.length; i++){
              $('#J_Nav').append("<li class='nav-item '><div id='s"+i+"' class='nav-item-inner'></div></li>");
              $('#s'+i).addClass(data[i].picture);
              $('#s'+i).text(data[i].text);
          }
            BUI.use('common/main',function(){
              var config = data;
              new PageUtil.MainPage({
                  modulesConfig : config
                });
          });  
      },  
      error : function() {  
        alert("初始化失败,请重新登录!")  
      }  
    });  
  </script>
</body>
</html>
