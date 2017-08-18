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
    <div style="margin:10px 0;"></div>
    <input id="message" type="hidden" value="${message }">
    <div id="searchCondition" style="width:98%;display: none;">
        <div style="font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;color: #A3A3A3;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td>员工编号:</td>
                    <td><input id="number" class="easyui-textbox" ></td>
                    <td>员工姓名:</td>
                    <td><input id="name" class="easyui-textbox" ></td>
                    <td>部门:</td>
                    <td><input id="departmentId" class="easyui-combotree" data-options="url:'<%=basePath%>department/selectDepartmentCondition.do',method:'get'" style="width:200px;" ></td>
                </tr>
                <tr>
                    <td>身份证号:</td>
                    <td><input id="idCard" class="easyui-textbox" ></td>
                    <td>文化程度:</td>
                    <td><input id="degree" class="easyui-textbox" ></td>
                    <td>是否可用:</td>
                    <td>
                        <select id="flag"   class="easyui-combobox"editable="false">
                            <option value=""></option>
                            <option value="1">可用</option>
                            <option value="0">禁用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>入职日期:</td>
                    <td><input id="inDate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" editable="false" ></input></td>
                    <td>离职日期:</td>
                    <td><input id="outDate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" editable="false"></input></td>
                </tr>
            </table>
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="20px;" cellpadding="0">
                <tr>
                   <td style="float: right;">
                        <input type="button" value="查询" onclick="search();" class="easyui-linkbutton " style="width: 60px;" >
                        <input type="button" value="重设" onclick="reset();" class="easyui-linkbutton " style="width: 60px;">
                   </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="showSelect"><center><img id="showImg" style="cursor: pointer;" src="<%=basePath%>img/main/menu_down.png"></center></div>
    <div style="margin:10px 0;"></div>
    <div id="easyuitable">
        <table id="dg" class="easyui-datagrid" title="员工信息" style="width:98%;">
    </div>
    </table>
<script language="javascript" src="<%=basePath%>js/jquery/jquery-3.1.1.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/easyui-lang-zh_CN.js"></script>
<script language="javascript" src="<%=basePath%>js/myjs/dateformat.js"></script>
<script language="javascript" src="<%=basePath%>jsp/personnel/employee/employee.js"></script>
</body>
</html>
