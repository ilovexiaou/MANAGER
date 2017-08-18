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
<div id="cc" >
    <div style="margin:10px 0;"></div>
    <input id="sid" type="hidden">
    <input id="sname" type="hidden">
    <input id="message" type="hidden" value="${message }">
    <div id="easyuitable">
        <table id="dg" class="easyui-datagrid" title="角色信息" style="width:98%;">
    </div>
    </table>
</div>
<script language="javascript" src="<%=basePath%>js/jquery/jquery-3.1.1.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/easyui-lang-zh_CN.js"></script>
<script language="javascript" src="<%=basePath%>js/myjs/dateformat.js"></script>
<script type="text/javascript">
$(function(){
	//增删改查 返回判断
    var message = $('#message').val();
    if(message!=undefined && message!=""){
        jQuery.messager.show({ 
            title:'操作提示:', 
            msg:message, 
            timeout:6000, 
            showType:'slide'
            }); 
    }
    //初始化table
    $('#dg').datagrid({
        url:basePath+'role/ajax.do',
        method:'post',
        iconCls: 'icon-man',rownumbers: true,singleSelect:true,collapsible:true,multiSort:true,
        nowrap: true,pageList: [10, 15, 20, 30 ,50],
        pagination:true,pageSize:10,toolbar:toolbar,
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'name',title:'角色名称',width:200},
            {field:'coment',title:'角色描述'},
            {field:'flag',title:'是否可用',width:100},
        ]],
        onClickRow: function(rowIndex,rowData){  
            var row = $('#dg').datagrid('getSelected');
            $('#sid').val(row.id);
            $('#sname').val(row.name);
        }
    });
    //$("#easyuitable").find(".panel").find(".datagrid-wrap").css("height", h + "px");
});

//table上的按钮初始化
var toolbar = [{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                window.location.href = basePath + 'role/addRole.do';
            }
        },{
            text:'修改',
            iconCls:'icon-edit',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    window.location.href = basePath + 'role/editRole.do?id='+row.id;
                }
            }
        },{
            text:'查看',
            iconCls:'icon-search',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    window.location.href = basePath + 'role/lookupRole.do?id='+row.id;
                }
            }
        },{
            text:'删除',
            iconCls:'icon-cancel',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    if(confirm("确定要删除这个角色吗？")){
                        window.location.href = basePath + 'role/deleteRole.do?id='+row.id;
                    }
                }
            }
}];

</script>
</body>
</html>
