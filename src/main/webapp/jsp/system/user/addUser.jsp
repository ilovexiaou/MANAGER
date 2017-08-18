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
<script type="text/javascript">
var basePath = "<%=basePath%>";
</script>
</head>
<body>
    <div id="mainFrame"  class="easyui-dialog" style="display: none;">
    </div>
    <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;新建用户信息</div>
    </div>
    <form action="<%=basePath%>user/saveUser.do"  method="post"  accept-charset="utf-8">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td style="width: 20%;">用户名:</td>
                    <td style="width: 80%;"><input id="userName" name="userName" class="easyui-textbox" required="required" ></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input id="p1" type="password" name="passWord" class="easyui-textbox" required="required" ></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input id="p2" type="password" class="easyui-textbox" required="required" ><span id="hint"></span></td>
                </tr>
                <tr>
                    <td>员工:</td>
                    <td>
                        <input type="hidden" id="employeeId" name="employeeId">
                        <input class="easyui-textbox" id="employeeName" name="employeeName">
                        <input  onclick="selectEmployee()" value="选择员工"  class="easyui-linkbutton "  style="width: 80px;height: 30px;">
                    </td>
                </tr>
                <tr>
                    <td>角色:</td>
                    <td>
                        <div>
                            <table style="width: 80%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                                <tr>
                                <c:forEach items="${roles }" var="t" varStatus="status">
                                    <td width="5%;">
	                                <input type="checkbox" name="roleIds" value="${t.id }" />
	                                </td>
	                                <td width="15%;">
	                                ${t.name } 
	                                </td>
	                                <c:if test="${(status.index+1)%5==0 }">
	                                    </tr>
	                                    <tr>
	                                </c:if>
	                            </c:forEach>
	                            </tr>
                            </table>
			            </div>
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
<script language="javascript" src="<%=basePath%>jsp/personnel/employee/employee.js"></script>
<script type="text/javascript">
function selectEmployee(){
	$('#mainFrame').empty();
	$('#mainFrame').append("<iframe id='selectIframe' src='<%=basePath%>employee/selectAllEmployee.do' frameborder='0' marginheight='0' marginwidth='0' height='100%;' width='100%;'></iframe>");
	$('#mainFrame').dialog({
        title: '请选择员工',
        left:10,
        top:10,
        width: 790,
        height: 650,
        cache: false,
        buttons: [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
                var sid =  $("#selectIframe").contents().find("#sid").val();
                var sname =  $("#selectIframe").contents().find("#sname").val();
                $('#employeeId').val(sid);
                $('#employeeName').textbox('setValue',sname);
                $('#mainFrame').dialog('close');
            }
        },{
            text:'取消',
            handler:function(){
                $('#mainFrame').dialog('close');
            }
        }],
         modal: true, 
    });
}
$(function(){
    $('#mainFrame').dialog('close');
    
    $("input",$("#p2").next("span")).blur(function(){  
        var pass1=$('#p1').val();
        var pass2=$('#p2').val();
        if(pass1 == pass2){
        	$('#hint').text("密码相同");
        	$('#hint').css("color","green");
        }else{
        	$('#hint').text("密码不一致,请重新输入");
        	$('#hint').css("color","red");
        }
        if(pass1=='' || pass2==''){
        	$('#hint').text("密码不能为空");
            $('#hint').css("color","red");
        }
    }) ;
    
    $("input",$("#p1").next("span")).blur(function(){  
        var pass1=$('#p1').val();
        var pass2=$('#p2').val();
        if(pass1 == pass2){
            $('#hint').text("密码相同");
            $('#hint').css("color","green");
        }else{
            $('#hint').text("密码不一致,请重新输入");
            $('#hint').css("color","red");
        }
        if(pass1=='' || pass2==''){
            $('#hint').text("密码不能为空");
            $('#hint').css("color","red");
        }
    }) ;
    
});

</script>
</body>
</html>
