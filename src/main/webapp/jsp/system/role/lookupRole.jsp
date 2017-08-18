<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<c:set var="string1" value="${role.flag }"/>
<c:if test="${role.flag=='true' }">
    <c:set var="sss" value="${fn:replace(string1, 'true', '启用')}" />
</c:if>
<c:if test="${role.flag=='false' }">
    <c:set var="sss" value="${fn:replace(string1, 'false', '禁用')}" />
</c:if>
    <div style="background-color: #E8E9EE;width: 100%;height: 40px;font-family: Arial;border-radius: 10px; border-style:solid;border-width: 0px;">
        <div style="padding-top: 10px;font-size: 14px;font-weight: bold;">&nbsp;&nbsp;修改角色信息</div>
    </div>
    <form action="<%=basePath%>role/updateRole.do"  method="post"  accept-charset="utf-8">
         <input type="hidden" id="ids" name="ids">
         <input type="hidden" id="id" name="id" value="${role.id} ">
         <div style="margin:10px 0;font-family: Arial;border-radius: 10px; border-style:solid; border-color: #E5E5E5;border-width: 1px;">
            <table style="width: 100%;font-size: 12px;"  border="0" cellspacing="5px;" cellpadding="0">
                <tr>
                    <td >角色名:</td>
                    <td> ${role.name}</td>
                    <td>是否启用:</td>
                    <td>${sss}</td>
                </tr>
                <tr>
                    <td>角色描述:</td>
                    <td colspan="3">
                        <input name="coment" readonly="readonly" value = "${role.coment}" class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:80px">
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
        url: '<%=basePath%>role/ajaxZtreeMenu.do',
        data: { 
        	id : $('#id').val()  
        },
        type:'post',
        traditional: true,
        async:false,
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
                var zTreeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
                zTreeObj.expandAll(true);
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
