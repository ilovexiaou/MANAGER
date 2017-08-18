package com.system.template.process.bean;

public class ProcessTemplateSub {
    private Integer id;
    private Integer templateId;// 模板主表id
    private String name;// 审批名称
    private Integer level;//审批级别
    private String audit_sign;//审批标志  字典表维护 1角色 2员工 3接口
    private Integer roleId;//角色id
    private Integer employeeId;//员工id
    private String interfaceName;//接口名称
    
    public String getAudit_sign() {
        return audit_sign;
    }
    public void setAudit_sign(String audit_sign) {
        this.audit_sign = audit_sign;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public String getInterfaceName() {
        return interfaceName;
    }
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    
}
