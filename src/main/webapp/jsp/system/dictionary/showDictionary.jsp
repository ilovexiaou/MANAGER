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
    <div id="searchCondition" style="width:98%;display: none;">
        <div style="font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;color: #A3A3A3;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td>字典种类:</td>
                    <td><input id="kind" class="easyui-textbox" ></td>
                    <td>名称:</td>
                    <td><input id="name" class="easyui-textbox" ></td>
                    <td></td>
                    <td></td>
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
        <table id="dg" class="easyui-datagrid" title="字典表" style="width:98%;">
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
        url:basePath+'dictionary/ajax.do',
        method:'post',
        iconCls: 'icon-man',rownumbers: true,singleSelect:true,collapsible:true,multiSort:true,
        nowrap: true,pageList: [10, 15, 20, 30 ,50],
        pagination:true,pageSize:10,toolbar:toolbar,
        columns:[[
                  {field:'id',title:'id',width:100,hidden:true},
                  {field:'name',title:'名称',width:200},
                  {field:'kind',title:'种类',width:200},
                  {field:'sequence',title:'序号',width:50},
                  {field:'comment',title:'备注'},
              ]]
    });
    //$("#easyuitable").find(".panel").find(".datagrid-wrap").css("height", h + "px");
});

//table上的按钮初始化
var toolbar = [{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                window.location.href = basePath + 'dictionary/addDictionary.do';
            }
        },{
            text:'修改',
            iconCls:'icon-edit',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    window.location.href = basePath + 'dictionary/editDictionary.do?id='+row.id;
                }
            }
        },{
            text:'查看',
            iconCls:'icon-search',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    window.location.href = basePath + 'dictionary/lookupDictionary.do?id='+row.id;
                }
            }
        },{
            text:'删除',
            iconCls:'icon-cancel',
            handler:function(){
                var row = $('#dg').datagrid('getSelected');
                if (row){
                    if(confirm("确定要删除吗？")){
                        window.location.href = basePath + 'dictionary/deleteDictionary.do?id='+row.id;
                    }
                }
            }
}];

//搜索按钮
function search(){
    var name = $('#name').val();
    var kind = $('#kind').val();
    
    $("#easyuitable").empty();
    $("#easyuitable").append("<table id='dg' class='easyui-datagrid' title='字典表' style='width:98%;'>");
    $('#dg').datagrid({
        url:basePath+'dictionary/ajaxByCondition.do',
        queryParams:{
            name : name,
            kind : kind,
        },
        method:'post',
        iconCls: 'icon-man',rownumbers: true,singleSelect:true,collapsible:true,multiSort:true,
        pagination:true,pageSize:10,toolbar:toolbar,
        columns:[[
                  {field:'id',title:'id',width:100,hidden:true},
                  {field:'name',title:'名称',width:200},
                  {field:'kind',title:'种类',width:200},
                  {field:'sequence',title:'序号',width:50},
                  {field:'comment',title:'备注'},
              ]]
    });
}

//重设
function reset(){
    $("#name").textbox('setValue','');
    $("#kind").textbox('setValue','');
}
</script>
</body>
</html>
