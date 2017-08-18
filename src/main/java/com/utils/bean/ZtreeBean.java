package com.utils.bean;

public class ZtreeBean {
    private Integer id;
    private Integer pId;
    private String name;
    private String isParent;//是否父节点
    
    public String getIsParent() {
        return isParent;
    }
    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getpId() {
        return pId;
    }
    public void setpId(Integer pId) {
        this.pId = pId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
