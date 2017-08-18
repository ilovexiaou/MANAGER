<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>js/treegid/easyui.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>js/treegid/icon.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/mytable.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var basePath = "<%=basePath%>";
</script>
</head>
<body>
    <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;新建字典表</div>
    </div>
    <form action="<%=basePath%>dictionary/saveDictionary.do"  method="post"  accept-charset="utf-8">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td style="width: 20%;">&nbsp;&nbsp;&nbsp;&nbsp;字典表种类:</td>
                    <td style="width: 80%;">${list[0].kind }</td>
                </tr>
            </table>
        </div>
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table id="dictionary" style="margin-bottom:10px;margin-left: 10px;margin-right: 10px;margin-top: 10px;width: 98%;"  class="table"  >
                <tr style="background-color: #E8E9EE;" align="center">
                    <td style="width: 20%;">名称</td>
                    <td style="width: 10%;">序号</td>
                    <td style="width: 70%;">备注</td>
                </tr>
                <c:forEach items="${list }" var="t">
                    <tr align="center">
	                    <td>${t.name }</td>
	                    <td>${t.sequence }</td>
	                    <td>${t.comment }</td>
	                </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="20px;" cellpadding="0">
                <tr>
                   <td align="center">
                        <input type="button" value="取消" onclick="back();" class="easyui-linkbutton " style="width: 80px;height: 35px;">
                   </td>
                </tr>
            </table>
        </div>
   </form>

<script language="javascript" src="<%=basePath%>js/treegid/jquery.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/easyui-lang-zh_CN.js"></script>
<script language="javascript" src="<%=basePath%>js/myjs/dateformat.js"></script>
<script type="text/javascript">
function back(){
    window.location.href = basePath + 'dictionary/getAll.do';
}
</script>
</body>
</html>
