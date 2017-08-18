package com.base.login.domain;

public class SecondMenu {
    private String id;
    private String collapsed;
    private String text;
    private ThirdMenu[] items;
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCollapsed() {
        return collapsed;
    }
    public void setCollapsed(String collapsed) {
        this.collapsed = collapsed;
    }
    public ThirdMenu[] getItems() {
        return items;
    }
    public void setItems(ThirdMenu[] items) {
        this.items = items;
    }
    
    
}
