package com.utils.quartz.bean;

public class AutoTask {
    private Integer id;
    private String name; //自动任务名称
    private String crom_time; //时间参数
    private Integer taskId; //任务关联表 id
    private String task_table; //关联表 名称
    private String remark;//备注
    private Integer createdId;
    private String createDateTime;
    
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
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
    public String getCrom_time() {
        return crom_time;
    }
    public void setCrom_time(String crom_time) {
        this.crom_time = crom_time;
    }
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public String getTask_table() {
        return task_table;
    }
    public void setTask_table(String task_table) {
        this.task_table = task_table;
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
