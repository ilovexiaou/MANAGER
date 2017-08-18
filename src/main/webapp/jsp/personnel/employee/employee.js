//table上的按钮初始化
var toolbar = [{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
            	window.location.href = basePath + 'employee/addEmployee.do';
            }
        },{
            text:'修改',
            iconCls:'icon-edit',
            handler:function(){
            	var row = $('#dg').datagrid('getSelected');
            	if (row){
            		window.location.href = basePath + 'employee/editEmployeeById.do?id='+row.id;
            	}
            }
        },{
            text:'查看',
            iconCls:'icon-search',
            handler:function(){
            	var row = $('#dg').datagrid('getSelected');
            	if (row){
            		window.location.href = basePath + 'employee/lookupEmployeeById.do?id='+row.id;
            	}
            }
        },{
            text:'删除',
            iconCls:'icon-cancel',
            handler:function(){
            	var row = $('#dg').datagrid('getSelected');
            	if (row){
            		if(confirm("确定要删除这名员工吗？")){
            			window.location.href = basePath + 'employee/deleteEmployee.do?id='+row.id;
            		}
            	}
            }
        }];

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
        url:basePath+'employee/ajax.do',
        method:'post',
        iconCls: 'icon-man',rownumbers: true,singleSelect:true,collapsible:true,multiSort:true,
        pagination:true,pageSize:10,toolbar:toolbar,
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'name',title:'员工姓名',width:100},
            {field:'number',title:'员工编号',width:100},
            {field:'companyName',title:'公司',width:200},
            {field:'departmentName',title:'部门',width:100},
            {field:'idCard',title:'身份证',width:100},
            {field:'degree',title:'文化程度',width:100},
            {field:'phone',title:'电话',width:100},
            {field:'inDate',title:'入职日期',width:100},
            {field:'outDate',title:'离职日期',width:100},
            {field:'post',title:'岗位',width:100},
            {field:'emergencyName',title:'紧急联系人',width:100},
            {field:'emergencyPhone',title:'紧急联系人电话',width:100},
            {field:'flag',title:'是否可用',width:100},
        ]],
    });
});

//搜索按钮
function search(){
	var flag = $('#flag').val();
	var departmentId = $('#departmentId').val();
	var name = $('#name').val();
	var number = $('#number').val();
	var idCard = $('#idCard').val();
	var degree = $('#degree').val();
	var inDate = $('#inDate').val();
	var outDate = $('#outDate').val();
	
	$("#easyuitable").empty();
    $("#easyuitable").append("<table id='dg' class='easyui-datagrid' title='员工信息' style='width:98%;height:390px'>");
    $('#dg').datagrid({
    	url:basePath+'employee/selectEmployee.do?',
    	queryParams:{
    		departmentId:departmentId,
    		flag:flag,
    		name:name,
    		number:number,
    		idCard:idCard,
    		degree:degree,
    		inDate:inDate,
    		outDate:outDate,
    	},
    	method:'post',
        iconCls: 'icon-man',rownumbers: true,singleSelect:true,collapsible:true,multiSort:true,
        pagination:true,pageSize:10,toolbar:toolbar,
        columns:[[
                {field:'id',title:'id',width:100,hidden:true},
				{field:'name',title:'员工姓名',width:100},
				{field:'number',title:'员工编号',width:100},
				{field:'companyName',title:'公司',width:100},
				{field:'departmentName',title:'部门',width:200},
				{field:'idCard',title:'身份证',width:100},
				{field:'degree',title:'文化程度',width:100},
				{field:'phone',title:'电话',width:100},
				{field:'inDate',title:'入职日期',width:100},
				{field:'outDate',title:'离职日期',width:100},
				{field:'post',title:'岗位',width:100},
				{field:'emergencyName',title:'紧急联系人',width:100},
				{field:'emergencyPhone',title:'紧急联系人电话',width:100},
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
//回到本菜单的首页
function back(){
	window.location.href = basePath + 'employee/getAll.do';
}