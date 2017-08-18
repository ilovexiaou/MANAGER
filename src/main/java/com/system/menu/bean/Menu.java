package com.system.menu.bean;

public class Menu {
    private Integer id;
    private Integer _parentId;//父节点
    private String name;
    private String href;
    private String level;//等级
    private String sequence;//排序
    private boolean flag;//禁用
    private String state; // 展开标志
    private String picture;//图片标志
    
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public Menu() {
        super();
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer get_parentId() {
        return _parentId;
    }
    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getSequence() {
        return sequence;
    }
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    
}
