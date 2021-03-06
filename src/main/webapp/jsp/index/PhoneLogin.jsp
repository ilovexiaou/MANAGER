<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>手机登录系统</title>
    <link rel="icon" href="<%=basePath%>img/index/neutronsoft.ico" type="image/x-icon">
    <link rel="shortcut icon" href="<%=basePath%>img/index/neutronsoft.ico" type="image/x-icon">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/htmleaf-demo.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/index.css">
</head>
<body class="form-bg">
    <div class="htmleaf-container">
        <div class="demo " style="padding: 160px 0;">
                <div class="container">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-6">
                            <form class="form-horizontal" action="http://192.168.101.152:8111/ssm4/loginPhoneLogin.do" method="get" >
                                <input type="hidden" name = 'uuid' value='${uuid }'>
                                <span class="heading">用户登录</span>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="inputEmail3" placeholder="用户名" name="username">
                                    <i class="fa fa-user"></i>
                                </div>
                                <div class="form-group help">
                                    <input type="password" class="form-control" id="inputPassword3" placeholder="密　码" name="password">
                                    <i class="fa fa-lock"></i>
                                    <!-- <a href="#" class="fa fa-question-circle"></a> -->
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default">手机绑定登录</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</body>
</html>



