<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>/js/treegid/easyui.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>/js/treegid/icon.css" rel="stylesheet" type="text/css">
</head>
<body>
<div style="margin:20px 0;"></div>
    <table id="tg" class="easyui-treegrid" title="组织结构" style="width:99%;"
            data-options="iconCls: 'icon-department',rownumbers: true,animate: true,collapsible: true,fitColumns: true,
            url: '<%=basePath%>department/ajax.do',method: 'get',toolbar:toolbar,
            idField: 'id',treeField: 'name',
            onClickCell: function(field,row){},">
        <thead>
            <tr>
                <th data-options="field:'name',width:100,editor:'text'">名称</th>
                <th data-options="field:'sequence',width:10,editor:'numberbox'">顺序</th>
                <th data-options="field:'flag',width:10,editor:'text'">是否可用</th>
            </tr>
        </thead>
    </table>
</body>
<script language="javascript" src="<%=basePath%>/js/jquery/jquery-3.1.1.min.js"></script>
<script language="javascript" src="<%=basePath%>/js/treegid/jquery.easyui.min.js"></script>
<script type="text/javascript">
        var editingId;
        //添加部门
        function add(){
            var node = $('#tg').treegrid('getSelected');
            if(null==node)return;
            var level = $('#tg').treegrid('getLevel',node.id);//第几级菜单
            var root = $('#tg').treegrid('getRoot',node.id);
            $.ajax({
                url : '<%=basePath%>department/add.do',// 跳转到 action 
                data : {
                    parentName : node.name ,
                    parentId : node.id,
                    rootName : root.name,
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
        //添加公司
        function addCompany(){
            $.ajax({
                url : '<%=basePath%>department/addCompany.do',// 跳转到 action 
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
                editingId = row.id
                $('#tg').treegrid('beginEdit', editingId);
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
                    url : '<%=basePath%>department/update.do',// 跳转到 action 
                    data : {
                        id: node.id,
                        name: node.name,
                        flag : node.flag,
                        sequence : node.sequence,
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
            if(!confirm("是否删除此部门以及此部门的所有下属部门？")){
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
                    url : '<%=basePath%>department/delete.do',// 跳转到 action 
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
</script>
<script type="text/javascript">
        var toolbar = [{
            text:'添加公司',
            iconCls:'icon-add',
            handler:function(){addCompany();}
        },{
            text:'添加部门',
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