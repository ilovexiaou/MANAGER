package com.system.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.menu.bean.Menu;

public interface MenuMapper {
    /**
     * 列出所有的模块
     */
    public List<Menu> getAll(@Param("flag") Integer flag);
    /**
     * 添加菜单
     */
    public void addMenu(Menu Menu);
    /**
     * 更新菜单
     * @param Menu
     */
    public void updateMenu(Menu Menu);
    /**
     * 根据id删除菜单
     * @param id
     */
    public void deleteMenu(Integer id);
    /**
     * 得到ztree结构的可用menu
     */
    public List<Menu> getTreeMenu();
}
