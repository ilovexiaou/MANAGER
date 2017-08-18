package com.system.template.process.bean;

public class ProcessTemplate {
    private Integer id;
    private String name;  //审批模板名称
    private String number; //审批模板唯一编号
    private Integer createdId; //创建人
    private String createDateTime; //创建时间
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
    
}
