<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id= "uploadForm"  method="post"  enctype="multipart/form-data">
上传文件
<input type="file" name="file">
<br>
<br>
<br>
<input type="button" value="上传啦" onclick="doUpload();">
</form>
<script language="javascript" src="<%=basePath%>js/treegid/jquery.min.js"></script>
<script type="text/javascript"> 
function doUpload() {  
    var formData = new FormData($( "#uploadForm" )[0]);  
    $.ajax({  
         url: '<%=basePath%>file/uploadAjax.do' ,  
         type: 'POST',  
         data: formData, 
         dataType:'json',  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (data) {  
        	 alert('上传成功'); 
        	 alert(data.success); 
         },  
         error: function (data) {  
             alert('上传失败');  
         }  
    });  
}  
</script>
</body>
</html>