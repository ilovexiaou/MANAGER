package com.personnel.vacation.bean;

public class Vacation {
    private Integer id;
    private String name; //请假人
    private Integer nameId; //请假人id
    private String startDateTime;//请假开始时间
    private String endDateTime;//请假结束时间
    private Integer countDate;//请假天数
    private String type;//请假类型
    private String comment;//请假事由
    private String auditStatus;//请假状态
    private String auditProcess;//请假进度
    
    private Integer createdId;//创建人id
    private String createDateTime;//创建时间
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
    public Integer getNameId() {
        return nameId;
    }
    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }
    public String getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }
    public String getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
    public Integer getCountDate() {
        return countDate;
    }
    public void setCountDate(Integer countDate) {
        this.countDate = countDate;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getAuditProcess() {
        return auditProcess;
    }
    public void setAuditProcess(String auditProcess) {
        this.auditProcess = auditProcess;
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
