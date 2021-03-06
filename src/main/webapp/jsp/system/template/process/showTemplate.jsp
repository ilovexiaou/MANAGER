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
    <input id="message" type="hidden" value="${message }">
    <div style="margin:10px 0;"></div>
    <div id="easyuitable">
        <table id="dg" class="easyui-datagrid" title="审批模板信息" style="width:98%;">
    </div>
    </table>
</div>
<script language="javascript" src="<%=basePath%>js/jquery/jquery-3.1.1.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/jquery.easyui.min.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/easyui-lang-zh_CN.js"></script>
<script language="javascript" src="<%=basePath%>js/myjs/dateformat.js"></script>
<script language="javascript" src="<%=basePath%>jsp/system/user/user.js"></script>
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
    //初始化,搜索栏 显示隐藏
    var main=true;
    $("#showImg").bind("click", function(){
        if(main){
            $("#showImg").attr('src',basePath+"img/main/menu_up.png");
            main=false;
        }else{
            $("#showImg").attr('src',basePath+"img/main/menu_down.png");
            main=true;
        }
        $("#searchCondition").toggle(500);
    });
    //初始化table
    $('#dg').datagrid({
        url:basePath+'template/process/ajax.do',
        method:'post',
        iconCls: 'icon-man',rownumbers: true,singleSelect:true,collapsible:true,multiSort:true,
        nowrap: true,pageList: [10, 15, 20, 30 ,50],
        pagination:true,pageSize:10,toolbar:toolbar,
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'name',title:'模板名称',width:100},
            {field:'number',title:'编号',width:100},
            {field:'createdId',title:'创建人id'},
            {field:'createDateTime',title:'创建时间',width:100},
        ]]
    });
    //$("#easyuitable").find(".panel").find(".datagrid-wrap").css("height", h + "px");
});

//table上的按钮初始化
var toolbar = [{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                window.location.href = basePath + 'template/process/addTemplate.do';
            }
        },{
            text:'修改',
            iconCls:'icon-edit',
            handler:function(){
            	alert("此功能开发中");
            	return false;
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    window.location.href = basePath + 'template/process/editTemplate.do?id='+row.id;
                }
            }
        },{
            text:'查看',
            iconCls:'icon-search',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    window.location.href = basePath + 'template/process/lookupTemplate.do?id='+row.id;
                }
            }
        },{
            text:'删除',
            iconCls:'icon-cancel',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    if(confirm("确定要删除这名员工吗？")){
                        window.location.href = basePath + 'template/process/deleteTemplate.do?id='+row.id;
                    }
                }
            }
}];

//搜索按钮
function search(){
    var flag = $('#flag').val();
    var userName = $('#userName').val();
    var employeeName = $('#employeeName').val();
    
    $("#easyuitable").empty();
    $("#easyuitable").append("<table id='dg' class='easyui-datagrid' title='用户信息' style='width:98%;'>");
    $('#dg').datagrid({
        url:basePath+'user/ajaxByCondition.do',
        queryParams:{
            flag:flag,
            userName:userName,
            employeeName:employeeName,
        },
        method:'post',
        iconCls: 'icon-man',rownumbers: true,singleSelect:true,collapsible:true,multiSort:true,
        pagination:true,pageSize:10,toolbar:toolbar,
        columns:[[
				{field:'id',title:'id',width:100,hidden:true},
				{field:'userName',title:'用户名',width:100},
				{field:'employeeName',title:'员工姓名',width:100},
				{field:'companyName',title:'公司名称'},
				{field:'departmentName',title:'部门名称',width:100},
				{field:'inDate',title:'入职日期',width:100},
				{field:'outDate',title:'离职日期',width:100},
				{field:'flag',title:'是否可用',width:100},
        ]]
    });
}

//重设
function reset(){
    $("#number").textbox('setValue','');
    $("#name").textbox('setValue','');
    $("#departmentId").textbox('setValue','');
    $("#idCard").textbox('setValue','');
    $("#degree").textbox('setValue','');
    $("#flag").textbox('setValue','');
    $("#inDate").textbox('setValue','');
    $("#outDate").textbox('setValue','');
}
</script>
</body>
</html>
