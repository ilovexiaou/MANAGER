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
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;新建请假单</div>
    </div>
    <form action="<%=basePath%>vacation/saveVacation.do"  method="post"  accept-charset="utf-8">
         <input type="hidden" name="createdId" value="${china_id }">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td >请假类型:</td>
                    <td >
                        <select class="easyui-combobox" name="type"  style="width:200px">
                            <option value="事假">事假</option>
                            <option value="年假">年假</option>
                            <option value="婚假">婚假</option>
                        </select>
                    </td>
                    <td >请假天数:</td>
                    <td ><input id="" name="countDate" class="easyui-textbox" required="required" ></td>
                </tr>
                <tr>
                    <td >请假开始时间:</td>
                    <td >
                    <input class="easyui-datetimebox" name="startDateTime" style="width:200px">
                    </td>
                    <td >请假结束时间:</td>
                    <td >
                    <input class="easyui-datetimebox"  name="endDateTime" style="width:200px">
                    </td>
                </tr>
                <tr>
                    <td >请假事由:</td>
                    <td colspan="3">
                        <input name="comment" class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:80px">
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
    $('#mainFrame').dialog('close');
    
    /* $("#auditSign").combobox({
    	onChange: function (n,o) {
    	   var flag = $('#auditSign').combobox('getText');
    	   if(flag=='人员'){
    		   $('#showRole').hide();
    		   $('#showEmployee').show();
    	   }else if(flag == '角色'){
    		   $('#showRole').show();
    		   $('#showEmployee').hide();
    	   }
    	}
    }); */
});
function back(){
    window.location.href = basePath + 'template/process/getAll.do';
}
function deleteSign(obj){
	$(obj.parentNode.parentNode).remove();
}
function selectEmployee(obj){
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
                //$(obj.parentNode).find('#auditIds').val(sid);
                //$(obj.parentNode).find('#auditNames').textbox('setValue',sname);
                //$('#auditNames').textbox('setValue',sname);
                $(obj.parentNode).children().eq(0).textbox('setValue',sname);
                $(obj.parentNode).children('input:eq(1)').val(sid);
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
function selectRole(obj){
    $('#mainFrame').empty();
    $('#mainFrame').append("<iframe id='selectIframe' src='<%=basePath%>role/getAll.do' frameborder='0' marginheight='0' marginwidth='0' height='100%;' width='100%;'></iframe>");
    $('#mainFrame').dialog({
        title: '请选择角色',
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
                //$(obj.parentNode).find('#auditIds').val(sid);
                //$(obj.parentNode).find('#auditNames').textbox('setValue',sname);
                //$('#auditNames').textbox('setValue',sname);
                $(obj.parentNode).children().eq(0).textbox('setValue',sname);
                $(obj.parentNode).children('input:eq(1)').val(sid)
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
function addAudit(){
	$('#myTable').append("<tr><td align='center'><input type='button' class='easyui-linkbutton' value='删除'  style='width: 60px;height: 25px;' onclick='deleteSign(this);'></td><td><input  name='levels' class='easyui-textbox' ></td><td><input  name='names' class='easyui-textbox' ></td><td><select  name='auditSigns' style='width:200px;' id='auditSign' onchange='selectSign(this);'><c:forEach items='${kindList }' var='t'><option value='${t.id }'>${t.name }</option></c:forEach></select></td><td><input id='auditNames' name='auditNames' class='easyui-textbox' ><input  hidden='hidden' id='auditIds' name='auditIds'  ><input  onclick='selectRole(this)' id='showRole' type='button'  value='选择角色'  ><input hidden='hidden' onclick='selectEmployee(this)' id='showEmployee'  type='button'  value='选择人员'  ></td></tr>");
	$.parser.parse();
	$('#mainFrame').dialog('close');
}
function selectSign(obj){
	var flag = $(obj).find("option:selected").text();
	if(flag=='人员'){
        $('#showRole',obj.parentNode.parentNode).hide();
        $('#showEmployee',obj.parentNode.parentNode).show();
    }else if(flag == '角色'){
        $('#showRole',obj.parentNode.parentNode).show();
        $('#showEmployee',obj.parentNode.parentNode).hide();
    }
}
</script>
</body>
</html>
