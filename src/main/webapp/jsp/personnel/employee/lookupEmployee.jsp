<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<c:set var="string1" value="${employee.flag }"/>
<c:if test="${employee.flag=='true' }">
    <c:set var="sss" value="${fn:replace(string1, 'true', '启用')}" />
</c:if>
<c:if test="${employee.flag=='false' }">
    <c:set var="sss" value="${fn:replace(string1, 'false', '禁用')}" />
</c:if>

    <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;查看员工信息</div>
    </div>
    <form action="<%=basePath%>employee/printEmployee.do"  method="post"  target="_blank"  accept-charset="utf-8">
         <input type="hidden" name="id" value="${employee.id }">
         <div style="margin:10px 0;">
            <table style="width: 100%;"  class="table">
                <tr>
                    <td style="width: 20%;">员工编号:</td>
                    <td style="width: 30%;">${employee.number }</td>
                    <td style="width: 20%;">员工姓名:</td>
                    <td style="width: 30%;">${employee.name }</td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td>${employee.idCard }</td>
                    <td>文化程度:</td>
                    <td>${employee.degree }</td>
                </tr>
                <tr>
                    <td>入职日期:</td>
                    <td>${employee.inDate }</td>
                    <td>离职日期:</td>
                    <td>${employee.outDate }</td>
                </tr>
                <tr>
                    <td>岗位:</td>
                    <td>${sss }</td>
                    <td>部门:</td>
                    <td>
                        <span id="departmentName"></span>
                        <div id="hid">
                        <input  id="departmentId" name="departmentId" class="easyui-combotree" data-options="url:'<%=basePath%>department/selectDepartmentCondition.do',method:'get'" style="width:200px;"  value="${employee.departmentId }">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>电话:</td>
                    <td>${employee.phone }</td>
                    <td>是否启用:</td>
                    <td>${sss}</td>
                </tr>
                <tr>
                    <td>紧急联系人:</td>
                    <td>${employee.emergencyName }</td>
                    <td>紧急联系人电话:</td>
                    <td>${employee.emergencyPhone }</td>
                </tr>
            </table>
        </div>
        <div>
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="20px;" cellpadding="0">
                <tr>
                   <td align="center">
                        <input type="submit" value="打印" class="easyui-linkbutton " style="width: 80px;height: 35px;">
                         &nbsp; &nbsp; &nbsp; &nbsp;
                        <input type="button" value="返回" onclick="back();" class="easyui-linkbutton " style="width: 80px;height: 35px;">
                   </td>
                </tr>
            </table>
        </div>
   </form>
<script language="javascript" src="<%=basePath%>js/jquery/jquery-3.1.1.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/easyui-lang-zh_CN.js"></script>
<script language="javascript" src="<%=basePath%>js/myjs/dateformat.js"></script>
<script language="javascript" src="<%=basePath%>jsp/personnel/employee/employee.js"></script>
<script type="text/javascript">
$(function(){
	$('#hid').hide();
	var departmentName = $("#departmentId").textbox('getText');
	$('#departmentName').text(departmentName);
});
</script>
</body>
</html>
