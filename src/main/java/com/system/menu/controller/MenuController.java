package com.system.menu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.base.constant.Constant;
import com.system.dictionary.bean.Dictionary;
import com.system.dictionary.service.DictionaryService;
import com.system.menu.bean.Menu;
import com.system.menu.service.MenuService;
import com.utils.json.JsonUtils;

@Controller
public class MenuController {
    @Resource
    MenuService menuService;
    @Resource
    DictionaryService dictionaryService;
    /**
     * 跳转所有菜单列表 不分页
     * @param model
     * @return
     */
    @RequestMapping("menu/getAll.do")
    public  String redirect(HttpServletRequest request){
        List<Dictionary> list = dictionaryService.getselectCondition("首页图标", null, 0, 20);
        request.setAttribute("list", list);
        return "/jsp/system/menu/showMenu";
    }
    /**
     * 展示所有的组织结构，包括禁用的，用json输出到页面
     * @param response
     */
    @RequestMapping(value = "menu/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response) {
        List<Menu> list = menuService.getAll(null);
        for (Menu menu : list) {
            if(menu.get_parentId()==null){
                menu.setState("closed");
            }
        }
        Map<String, List<Menu>> map=new HashMap<String, List<Menu>>();
        map.put("rows", list);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 添加新模块，新添加的模块默认为false，需要重新保存才能启用
     * @param response
     * @param request
     */
    @RequestMapping(value = "menu/add.do", method = RequestMethod.POST)
    public void addMenu(HttpServletResponse response,HttpServletRequest request) {
        Menu mn = new Menu();
        String parentId = request.getParameter("parentId");
        String level = request.getParameter("level");
        if(null!= parentId && ""!=parentId)mn.set_parentId(Integer.valueOf(parentId));
        mn.setName("请修改菜单名称");
        mn.setFlag(false);
        mn.setSequence("100");
        if(null!= level && ""!=level){
            mn.setLevel(level);
        }else{
            mn.setLevel("1");
        }
        menuService.addMenu(mn);
        try {
            JsonUtils.convertListToJson(mn, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 修改菜单
     */
    @RequestMapping(value = "menu/updateMenu.do", method = RequestMethod.POST)
    public void updateMenu(HttpServletResponse response,HttpServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String flag = request.getParameter("flag");
        String sequence = request.getParameter("sequence");
        String href = request.getParameter("href");
        String picture = request.getParameter("picture");
        Menu mn = new Menu();
        mn.setId(Integer.valueOf(id));
        mn.setName(name);
        mn.setSequence(sequence);
        if(flag.equals(Constant.TRUE)){
            mn.setFlag(true);
        }else{
            mn.setFlag(false);
        }
        mn.setHref(href);
        mn.setPicture(picture);
        menuService.updateMenu(mn);
        List<String> list = new ArrayList<String>();
        list.add("success");
        try {
            JsonUtils.convertListToJson(list, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "menu/deleteMenu.do", method = RequestMethod.POST)
    public void deleteMenu(HttpServletResponse response,HttpServletRequest request){
        String deleteids = request.getParameter("deleteids");
        if(null!=deleteids && !"".equals(deleteids)){
            String[] ids = deleteids.split(",");
            for (String id : ids) {
                menuService.deleteMenu(Integer.valueOf(id));
            }
        }
        List<String> list = new ArrayList<String>();
        list.add("success");
        try {
            JsonUtils.convertListToJson(list, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 以ztree结构输出树形结构
     * @param response
     * @param request
     */
    @RequestMapping(value = "menu/ajaxZtreeMenu.do", method = RequestMethod.POST)
    public void getZtreeMenu(HttpServletResponse response,HttpServletRequest request){
        List<Menu> treeMenu = menuService.getTreeMenu();
        try {
            JsonUtils.convertListToJson(treeMenu, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
