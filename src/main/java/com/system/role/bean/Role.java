package com.system.role.bean;

public class Role {
    private Integer id;
    private String name;
    private String coment;
    private boolean flag;
    
    private Integer menuId;
    private String  ids;//menu id集合字符串 ，以逗号结尾
    
    
    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getComent() {
        return coment;
    }
    public void setComent(String coment) {
        this.coment = coment;
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
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
}
