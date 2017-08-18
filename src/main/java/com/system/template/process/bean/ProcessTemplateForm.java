package com.system.template.process.bean;

public class ProcessTemplateForm {
    private Integer id;
    private String name;  //审批模板名称
    private String number; //审批模板唯一编号
    private Integer createdId; //创建人
    private String createDateTime; //创建时间
    
    private Integer[] ids;
    private Integer[] templateIds;// 模板主表id
    private String[] names;// 审批名称
    private Integer[] levels;//审批级别
    private String[] auditSigns;//审批标志  字典表维护 1角色 2员工 3接口
    private String[] auditIds;//审批标志id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Integer[] getIds() {
        return ids;
    }
    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
    public Integer[] getTemplateIds() {
        return templateIds;
    }
    public void setTemplateIds(Integer[] templateIds) {
        this.templateIds = templateIds;
    }
    public String[] getNames() {
        return names;
    }
    public void setNames(String[] names) {
        this.names = names;
    }
    public Integer[] getLevels() {
        return levels;
    }
    public void setLevels(Integer[] levels) {
        this.levels = levels;
    }
    public String[] getAuditSigns() {
        return auditSigns;
    }
    public void setAuditSigns(String[] auditSigns) {
        this.auditSigns = auditSigns;
    }
    public String[] getAuditIds() {
        return auditIds;
    }
    public void setAuditIds(String[] auditIds) {
        this.auditIds = auditIds;
    }
    
    
}
