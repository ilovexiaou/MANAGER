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
    <form action="<%=basePath%>dictionary/updateDictionary.do"  method="post"  accept-charset="utf-8">
         <input type="hidden" name="oldIds" value="${oldIds }">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td style="width: 20%;">&nbsp;&nbsp;&nbsp;&nbsp;字典表种类:</td>
                    <td style="width: 80%;"><input name="kind" class="easyui-textbox" value="${list[0].kind}" required="required" ></td>
                </tr>
            </table>
        </div>
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table id="dictionary" style="margin-bottom:10px;margin-left: 10px;margin-right: 10px;margin-top: 10px;width: 98%;"  class="table"  >
                <tr style="background-color: #E8E9EE;" align="center">
                    <td style="width: 10%;"><input type="button" value="添加" id="add"></td>
                    <td style="width: 20%;">名称</td>
                    <td style="width: 10%;">序号</td>
                    <td style="width: 60%;">备注</td>
                </tr>
                <c:forEach items="${list }" var="t" >
                    <tr align="center">
	                    <td>
	                    <input type="button" value="删除" onclick="deleteRow(this);" >
	                    <input type="hidden" name="ids" value="${t.id }"> 
	                    </td>
	                    <td><input style="width: 100%;" type="text" name="names" class="easyui-textbox"  required="required" value="${t.name }"></td>
	                    <td><input style="width: 100%;" type="text" name="sequences" class="easyui-textbox" value="${t.sequence }"></td>
	                    <td><input style="width: 100%;" type="text" name="comments" class="easyui-textbox" value="${t.comment }"></td>
	                </tr>
                </c:forEach>
                
            </table>
        </div>
        <div>
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="20px;" cellpadding="0">
                <tr>
                   <td align="center">
                        <input id="ok" type="submit" value="确定"  class="easyui-linkbutton "  style="width: 80px;height: 35px;" >
                        &nbsp;&nbsp;
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
$(function(){
    $("#add").click(function(){
    	$("#dictionary").append("<tr align='center'><td><input type='button' value='删除' onclick='deleteRow(this);' ><input type='hidden' name='ids' > </td><td><input style='width: 100%;' type='text' name='names'  required='required'  class='easyui-textbox'></td><td><input style='width: 100%;' type='text' name='sequences' class='easyui-textbox'></td><td><input style='width: 100%;' type='text' name='comments' class='easyui-textbox'></td></tr>");
    	$.parser.parse();
    });
});

function deleteRow(obj){
	obj.parentNode.parentNode.remove();
}
function back(){
	window.location.href = basePath + 'dictionary/getAll.do';
}
</script>
</body>
</html>
