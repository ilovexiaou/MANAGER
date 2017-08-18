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
    <title>管理系统登录页</title>
    <link rel="icon" href="img/index/neutronsoft.ico" type="image/x-icon">
    <link rel="shortcut icon" href="img/index/neutronsoft.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/htmleaf-demo.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body class="form-bg">
    <div class="htmleaf-container">
        <div class="demo " style="padding: 160px 0;">
                <div class="container">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-6">
                            <form id="form" class="form-horizontal" action="login.do" method="post" >
                                <span class="heading">
                                    <div id="qrcode"> 用户登录&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                </span>
                                
                                <div class="form-group">
                                    <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
                                    <i class="fa fa-user"></i>
                                </div>
                                <div class="form-group help">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="密　码">
                                    <i class="fa fa-lock"></i>
                                    <!-- <a href="#" class="fa fa-question-circle"></a> -->
                                </div>
                                <div class="form-group">
                                    <div class="main-checkbox">
                                        <input type="checkbox" value="None" id="checkbox1" name="check"/>
                                        <label for="checkbox1"></label>
                                    </div>
                                    <span class="text">记住我</span>
                                    <button id="sub" type="submit" class="btn btn-default">登录</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</body>
<script language="javascript" src="js/jquery/jquery-3.1.1.min.js"></script>
<script language="javascript" src="js/qrcode/jquery.qrcode.min.js"></script>
<script>
var id=getUuid();
var path ='<%=basePath%>';
$(document).ready(function(){
	//alert(path+'rediectPhoneLogin?uuid='+id);
  //$("#qrcode").qrcode({width:100,height:100,correctLevel:0,text:'http://192.168.101.152:8111/ssm4/loginrediectPhoneLogin?uuid='+id});
  //window.setInterval(function(){pollAjax()},20000);//20秒
});
function getUuid(){
  var len=32;//32长度
  var radix=16;//16进制
  var chars='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');var uuid=[],i;radix=radix||chars.length;if(len){for(i=0;i<len;i++)uuid[i]=chars[0|Math.random()*radix];}else{var r;uuid[8]=uuid[13]=uuid[18]=uuid[23]='-';uuid[14]='4';for(i=0;i<36;i++){if(!uuid[i]){r=0|Math.random()*16;uuid[i]=chars[(i==19)?(r&0x3)|0x8:r];}}}
  return uuid.join('');
}  

function pollAjax(){
	
	$.ajax({
        url : path+'sendMessage?uuid='+id,// 轮询扫码登录
        async: false,
        data : {
            notic : '123'
        },
        serverSide: true,//服务器处理 
        traditional : true,
        type : 'post',
        success : function(data) {
        	var dataObject = eval(data);
            if('scueess'== dataObject){
            	$('#username').val('admin');
            	$('#password').val('12345');
            	$('#form').submit();
            }
        },
        error : function() {
            alert("异常！");
        }
    });
}
</script>
</html>



