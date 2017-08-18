package com.system.menu.service;

import java.util.List;

import com.system.menu.bean.Menu;

public interface MenuService {
    public List<Menu> getAll(Integer flag);
    public void addMenu(Menu Menu);
    public void updateMenu(Menu Menu);
    public void deleteMenu(Integer id);
    public List<Menu> getTreeMenu();
}
