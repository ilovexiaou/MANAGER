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
<link href="<%=basePath%>js/ztree/zTreeStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var basePath = "<%=basePath%>";
</script>
</head>
<body>
    <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;新建角色信息</div>
    </div>
    <form action="<%=basePath%>role/saveRole.do"  method="post"  accept-charset="utf-8">
         <input type="hidden" id="ids" name="ids">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td >角色名:</td>
                    <td> <input  name="name" class="easyui-textbox" required="required" ></td>
                    <td>是否启用:</td>
                    <td>
                        <span class="radioSpan" >
                            <input name="flag" type="radio" name="flag" value="1" checked="checked">启用</input>
                            <input name="flag" type="radio" name="flag" value="0">禁用</input>
                        </span>
                    </td>
                </tr>
                <tr>
                    <td>角色描述:</td>
                    <td colspan="3">
                        <input name="coment" class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:80px">
                    </td>
                </tr>
            </table>
        </div>
        <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
            <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;关联菜单信息</div>
        </div>
        <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
		    <ul id="treeDemo" class="ztree" style="margin-top: 0; width: 100%;">  
            </ul>  
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
<script language="javascript" src="<%=basePath%>js/myjs/dateformat.js"></script>
<script language="javascript" src="<%=basePath%>js/ztree/jquery.ztree.core.js"></script>
<script language="javascript" src="<%=basePath%>js/ztree/jquery.ztree.excheck.js"></script>
<script language="javascript" src="<%=basePath%>js/treegid/easyui-lang-zh_CN.js"></script>
</body>
<script type="text/javascript">
$(function(){
    $.ajax({ 
        url: '<%=basePath%>menu/ajaxZtreeMenu.do',
        data: { 
            name : '1'  //随便写的，传入后台的值
        },
        type:'post',
        traditional: true,
        success: function(data){
            var dataObj = eval(data);
            var zTreeObj;
            var setting = {
                    data: {  
                        simpleData: {  
                            enable:true,  
                            /* idKey: "id",  
                            pIdKey: "pId" */  
                        }  
                    },
                    check: {
                        enable: true,
                        chkboxType :{ "Y" : "ps", "N" : "ps" } //Y:勾选（参数：p:影响父节点），N：不勾（参数s：影响子节点）[p 和 s 为参数，参数都不写""为全不影响]
                    },
                    callback: {
                        onCheck: 
                             function() {  
                                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                                    var checkCount = zTree.getCheckedNodes(true);
                                    var classpurview = "";
                                    for(var i=0;i<checkCount.length;i++) {
                                           classpurview +=  checkCount[i].id+","      //存入数据的id，比如这种形式"1,2,5,"  后台截取下
                                    }
                                    $('#ids').val(classpurview);
                                    //alert(classpurview); 
                            } ,
                    },
                    view: {  
                        showLine: true,  
                        showIcon: true,  
                        dblClickExpand: true  
                    }, 
            };
            var zNodes = dataObj; 
            $(document).ready(function(){
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            });
        },
        error : function() {    
            alert("异常！");    
       }
    });
});

//回到本菜单的首页
function back(){
    window.location.href = basePath + 'role/getAll.do';
}
</script>
</html>
