package com.base.login.domain;


public class FirstMenu {
    private String id;
    private String homePage;//默认首页展示标志 与前台对应，写死了是code
    private String text;//后台标题名称
    private String picture;//后台标题图标
    private SecondMenu[] menu;
    
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
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
    public String getHomePage() {
        return homePage;
    }
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
    public SecondMenu[] getMenu() {
        return menu;
    }
    public void setMenu(SecondMenu[] menu) {
        this.menu = menu;
    }
    
    
}
