package com.personnel.department.bean;
/**
 * 部门
 * @author Administrator
 *
 */
public class Department {
    private Integer id;
    private Integer _parentId;;//父节点
    private String parentName;//上级部门名称
    private String rootName;//公司名称
    private String name;//部门名称
    private String sequence;//排序
    private boolean flag;//禁用
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    public String getRootName() {
        return rootName;
    }
    public void setRootName(String rootName) {
        this.rootName = rootName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public Integer get_parentId() {
        return _parentId;
    }
    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
    }
    
}
