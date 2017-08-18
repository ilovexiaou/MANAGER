package com.system.template.record.bean;

public class AuditRecord {
    private Integer id;
    private Integer billId; //任务id
    private String billTable; //任务表名
    private String name; //审批名称
    private String number; //审批编号
    private Integer templateId; //审批模板id
    private Integer template_subId; //审批模板明细id
    private int level; //审批级别
    private Integer roleId; 
    private Integer employeeId;
    private String audit_status;//审批状态
    private String audit_result;//审批结果
    private String audit_comment;//审批回复内容
    private Integer auditedId;//审批人id
    private String auditedDateTime;//审批时间
    private Integer createdId;//创建人id
    private String createDateTime;//创建时间
    
    
    public String getBillTable() {
        return billTable;
    }
    public void setBillTable(String billTable) {
        this.billTable = billTable;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBillId() {
        return billId;
    }
    public void setBillId(Integer billId) {
        this.billId = billId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Integer getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public Integer getTemplate_subId() {
        return template_subId;
    }
    public void setTemplate_subId(Integer template_subId) {
        this.template_subId = template_subId;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
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
    public String getAudit_status() {
        return audit_status;
    }
    public void setAudit_status(String audit_status) {
        this.audit_status = audit_status;
    }
    public String getAudit_result() {
        return audit_result;
    }
    public void setAudit_result(String audit_result) {
        this.audit_result = audit_result;
    }
    public String getAudit_comment() {
        return audit_comment;
    }
    public void setAudit_comment(String audit_comment) {
        this.audit_comment = audit_comment;
    }
    public Integer getAuditedId() {
        return auditedId;
    }
    public void setAuditedId(Integer auditedId) {
        this.auditedId = auditedId;
    }
    public String getAuditedDateTime() {
        return auditedDateTime;
    }
    public void setAuditedDateTime(String auditedDateTime) {
        this.auditedDateTime = auditedDateTime;
    }
    public Integer getCreatedId() {
        return createdId;
    }
    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }
    public String getCreateDateTime() {
        return createDateTime;
    }
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }
    
}
