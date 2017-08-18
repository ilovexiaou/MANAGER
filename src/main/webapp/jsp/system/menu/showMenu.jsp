<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>/js/treegid/easyui.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>/js/treegid/icon.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="dlg" class="easyui-dialog" title="请选择导航栏图标" style="width:400px;height:200px;padding:10px;left: 100px;top: 100px;" 
            data-options="
                iconCls: 'icon-save',
                closed: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        cedit($('#s1').val(),$('#s1').find('option:selected').text());
                        $('#s1').val('-1');
                        $('#dlg').dialog('close');
                    }
                },{
                    text:'取消',
                    handler:function(){
                        dedit($('#s1').val(),$('#s1').find('option:selected').text());
                        $('#dlg').dialog('close');
                    }
                }]
            ">
        <select id="s1" >
            <c:forEach items="${list }" var="t">
                <option value="${t.name }">${t.comment }</option>
            </c:forEach>
        </select>
        <input type="hidden" id="hid">
</div>

<div style="margin:20px 0;"></div>
<div>
    <span style="color: #A3A3A3;font-size: 12px;">注意：1等级为1的模块为上方标题栏(标题栏不需要地址)，2等级为2的模块为左侧标题栏(左侧标题栏不需要地址)，3等级为3的模块为左侧标题下的内容(需要地址)</span>
</div>
<div style="margin:20px 0;"></div>
    <table id="tg" class="easyui-treegrid" title="模块信息" style="width:99%;"
            data-options="iconCls: 'icon-department',rownumbers: true,animate: true,collapsible: true,fitColumns: true,
            url: '<%=basePath%>menu/ajax.do',method: 'post',toolbar:toolbar,
            idField: 'id',treeField: 'name',
            onClickCell: function(field,row){},
            ">
        <thead>
            <tr>
                <th data-options="field:'name',width:100,editor:'text'">模块名称</th>
                <th data-options="field:'href',width:300,editor:'text'">模块地址</th>
                <th data-options="field:'sequence',width:30,editor:'numberbox'">顺序</th>
                <th data-options="field:'picture',width:50,editor:'text'">图片</th>
                <th data-options="field:'flag',width:30,editor:'text'">是否可用</th>
            </tr>
        </thead>
    </table>
</body>
<script language="javascript" src="<%=basePath%>/js/jquery/jquery-3.1.1.min.js"></script>
<script language="javascript" src="<%=basePath%>/js/treegid/jquery.easyui.min.js"></script>
<script type="text/javascript">
        var editingId;
        //添加子菜单
        function add(){
            var node = $('#tg').treegrid('getSelected');
            if(null==node)return;
            var level = $('#tg').treegrid('getLevel',node.id);//第几级菜单
            var root = $('#tg').treegrid('getRoot',node.id);
            if(level==3){
            	alert("最多3级菜单！");
            	return;
            }
            $.ajax({
                url : '<%=basePath%>menu/add.do',// 跳转到 action 
                data : {
                    parentId : node.id,
                    level : level+1,
                },
                serverSide: true,//服务器处理 
                traditional : true,
                type : 'post',
                success : function(data) {
                    //alert(data);
                    var objData = eval("("+data+")");
                    //alert(objData.id);
                     $('#tg').treegrid('append',{
                        parent: node.id,  
                        data: [{
                            id: objData.id,
                            name: objData.name,
                            flag : objData.flag ,
                            sequence : objData.sequence,
                        }]
                    }); 
                },
                error : function() {
                    alert("异常！");
                }
            });
        }
        //添加1级模块
        function addFirst(){
            $.ajax({
                url : '<%=basePath%>menu/add.do',// 跳转到 action 
                data : {
                    rootName : '',
                },
                serverSide: true,//服务器处理 
                traditional : true,
                type : 'post',
                success : function(data) {
                    //alert(data);
                    var objData = eval("("+data+")");
                    //alert(objData.id);
                     $('#tg').treegrid('append',{
                        parent: null,  
                        data: [{
                            id: objData.id,
                            name: objData.name,
                            flag : objData.flag ,
                            sequence : objData.sequence,
                        }]
                    }); 
                },
                error : function() {
                    alert("异常！");
                }
            });
        }
        //修改
        function edit(){
            if (editingId != undefined){
                $('#tg').treegrid('select', editingId);
                return;
            }
            var row = $('#tg').treegrid('getSelected');
            if (row){
                editingId = row.id;
                if(row.level==1){
                	$('#hid').val(editingId);
                    $('#dlg').dialog('open');
                }else{
                   $('#tg').treegrid('beginEdit', editingId);
                }
                
            }
        }
        //保存
        function save(){
            if (editingId != undefined){
                var node = $('#tg').treegrid('getSelected');
                var t = $('#tg');
                t.treegrid('endEdit', editingId);
                editingId = undefined;
                var persons = 0;
                var rows = t.treegrid('getChildren');
                for(var i=0; i<rows.length; i++){
                    var p = parseInt(rows[i].persons);
                    if (!isNaN(p)){
                        persons += p;
                    }
                }
                $.ajax({
                    url : '<%=basePath%>menu/updateMenu.do',// 跳转到 action 
                    data : {
                        id: node.id,
                        name: node.name,
                        href: node.href,
                        flag : node.flag,
                        sequence : node.sequence,
                        picture : node.picture
                    },
                    serverSide: true,//服务器处理 
                    traditional : true,
                    type : 'post',
                    success : function(data) {
                    },
                    error : function() {
                        alert("异常！");
                    }
                });
            }
        }
        function cancel(){
            if (editingId != undefined){
                $('#tg').treegrid('cancelEdit', editingId);
                editingId = undefined;
            }
        }
        // 删除
        function deleteRow(){
            if(!confirm("是否删除此菜单以及此菜单的所有下属菜单？")){
                return;
            }
            var row = $('#tg').treegrid('getSelected');
            var strs=$('#tg').treegrid('getChildren', row.id);
            var re=row.id;
            for(var i=0 ; i<strs.length ; i++){
                    re=re+','+strs[i].id;
            }
            if (row){
                $.ajax({
                    url : '<%=basePath%>menu/deleteMenu.do',// 跳转到 action 
                    data : {
                        id : row.id ,
                        deleteids : re ,
                    },
                    serverSide: true,//服务器处理 
                    traditional : true,
                    type : 'post',
                    success : function(data) {
                        //alert(data);
                        //var objData = eval("("+data+")");
                        editingId = row.id;
                        $('#tg').treegrid('remove', editingId);
                        editingId = undefined;
                    },
                    error : function() {
                        alert("异常！");
                    }
                });
            }
        }
        
        function cedit(value,text){
            $('#tg').treegrid('update',{
                id: $('#hid').val(),
                row: {
                	picture: value,
                }
            });
            $('#tg').treegrid('beginEdit',$('#hid').val());
        }
        function dedit(value,text){
            $('#tg').treegrid('beginEdit',$('#hid').val());
        }
</script>
<script type="text/javascript">
        var toolbar = [{
            text:'添加1级模块',
            iconCls:'icon-add',
            handler:function(){addFirst();}
        },{
            text:'添加子模块',
            iconCls:'icon-add',
            handler:function(){add();}
        },{
            text:'修改',
            iconCls:'icon-edit',
            handler:function(){edit();}
        },{
            text:'保存',
            iconCls:'icon-save',
            handler:function(){save();}
        },{
            text:'取消',
            iconCls:'icon-undo',
            handler:function(){cancel();}
        },{
            text:'删除',
            iconCls:'icon-cancel',
            handler:function(){deleteRow();}
        }];

</script>
</html>