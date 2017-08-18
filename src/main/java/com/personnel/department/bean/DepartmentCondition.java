package com.personnel.department.bean;

import java.util.ArrayList;
import java.util.List;

public class DepartmentCondition {
    private Integer id;
    private String text;
    private List<DepartmentCondition> children = new ArrayList<DepartmentCondition>();
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<DepartmentCondition> getChildren() {
        return children;
    }
    public void setChildren(List<DepartmentCondition> children) {
        this.children = children;
    }
}
