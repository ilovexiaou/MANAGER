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
    <form action="<%=basePath%>vacation/updateApprovalVacation.do"  method="post"  accept-charset="utf-8">
    <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;审批请假单</div>
    </div>
    
         <input type="hidden" name="auditedId" value="${china_id }">
         <input type="hidden" name="billId" value="${vacation.id }">
         <input type="hidden" id="cc" value="${vacation.type }">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td style="width: 10%;" >请假类型:</td>
                    <td style="width: 40%;" >
                        <select class="easyui-combobox" id="type" name="type"  style="width:200px">
                            <option value="事假">事假</option>
                            <option value="年假">年假</option>
                            <option value="婚假">婚假</option>
                        </select>
                    </td>
                    <td  style="width: 10%;">请假天数:</td>
                    <td  style="width: 40%;"><input id="" name="countDate" class="easyui-textbox" required="required" value="${vacation.countDate }"></td>
                </tr>
                <tr>
                    <td >请假开始时间:</td>
                    <td >
                    <input class="easyui-datetimebox" name="startDateTime" style="width:200px" value="${vacation.startDateTime }">
                    </td>
                    <td >请假结束时间:</td>
                    <td >
                    <input class="easyui-datetimebox"  name="endDateTime" style="width:200px" value="${vacation.endDateTime }">
                    </td>
                </tr>
                <tr>
                    <td >请假事由:</td>
                    <td colspan="3">
                        <input name="comment" class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:80px" value="${vacation.comment }">
                    </td>
                </tr>
            </table>
        </div>
        
        <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
            <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;上级审批记录</div>
        </div>
        <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table id="dictionary" style="margin-bottom:10px;margin-left: 10px;margin-right: 10px;margin-top: 10px;width: 98%;"  class="table"  >
                <tr style="background-color: #E8E9EE;" align="center">
                    <td style="width: 10%;">序号</td>
                    <td style="width: 20%;">审批名称</td>
                    <td style="width: 10%;">审批状态</td>
                    <td style="width: 10%;">审批结果</td>
                    <td style="width: 50%;">审批意见</td>
                </tr>
                <c:forEach items="${list }" var="t" varStatus="no">
                    <tr>
                        <td >${no.index+1 }</td>
                        <td >${t.name }</td>
                        <td >${t.audit_status }</td>
                        <td >${t.audit_result }</td>
                        <td >${t.audit_comment }</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
            <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;本级审批</div>
        </div>
        <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td  style="width: 10%;">审核结果:</td>
                    <td  style="width: 90%;">
                        <select class="easyui-combobox" id="type" name="audit_result"  style="width:200px">
                            <option value="通过">通过</option>
                            <option value="不通过">不通过</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td >审核意见:</td>
                    <td >
                        <input name="audit_comment" class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:80px" >
                    </td>
                </tr>
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
	$('#type').combobox('setValue',$('#cc').val());
	
});
function back(){
    window.location.href = basePath + 'template/process/getAll.do';
}
</script>
</body>
</html>
