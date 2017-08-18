<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>js/treegid/easyui.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>js/treegid/icon.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var basePath = "<%=basePath%>";
</script>
</head>
<body>
    <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;新建员工信息</div>
    </div>
    <form action="<%=basePath%>employee/saveEmployee.do"  method="post"  accept-charset="utf-8">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td style="width: 20%;">员工编号:</td>
                    <td style="width: 30%;"><input id="number" name="number" class="easyui-textbox" required="required" ></td>
                    <td style="width: 20%;">员工姓名:</td>
                    <td style="width: 30%;"><input id="name" name="name" class="easyui-textbox" required="required" ></td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td><input id="idCard" name="idCard" class="easyui-textbox" required="required" ></td>
                    <td>文化程度:</td>
                    <td><input id="degree" name="degree" class="easyui-textbox" ></td>
                </tr>
                <tr>
                    <td>入职日期:</td>
                    <td><input id="inDate" name="inDate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" editable="false"  required="required"></input></td>
                    <td>部门:</td>
                    <td><input id="departmentId" name="departmentId" class="easyui-combotree" data-options="url:'<%=basePath%>department/selectDepartmentCondition.do',method:'get'" style="width:200px;" ></td>
                </tr>
                <tr>
                    <td>电话:</td>
                    <td><input id="phone" name="phone" class="easyui-textbox" ></td>
                    <td>岗位:</td>
                    <td><input id="post" name="post" class="easyui-textbox" ></td>
                </tr>
                <tr>
                    <td>紧急联系人:</td>
                    <td><input id="emergencyName" name="emergencyName" class="easyui-textbox" ></td>
                    <td>紧急联系人电话:</td>
                    <td><input id="emergencyPhone" name="emergencyPhone" class="easyui-textbox" ></td>
                </tr>
            </table>
        </div>
        <div>
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="20px;" cellpadding="0">
                <tr>
                   <td align="center">
                        <input type="submit" value="确定"  class="easyui-linkbutton " style="width: 80px;height: 35px;" >
                        &nbsp;&nbsp;
                        <input type="button" value="取消" onclick="back();" class="easyui-linkbutton " style="width: 80px;height: 35px;">
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
</body>
</html>
