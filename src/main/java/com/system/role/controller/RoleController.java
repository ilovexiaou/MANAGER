package com.system.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.system.menu.bean.Menu;
import com.system.role.bean.Role;
import com.system.role.service.RoleService;
import com.utils.bean.ZtreeBean;
import com.utils.json.JsonUtils;

@Controller
public class RoleController {
    @Resource
    RoleService roleService;
    
    @RequestMapping("role/getAll.do")
    public  String listAll(){
        return "/jsp/system/role/showRole";
    }
    
    @RequestMapping(value = "role/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response,HttpServletRequest request) {
        String flag = request.getParameter("enabled");//选择员工iframe，页面判断
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<Role> roleList;
        int count;
        Map<String, Object> map;
        if(null!=flag && flag!=""){//启用的员工查询
            roleList = roleService.getPageRole(offset, limit, true);
            count = roleService.countAll(true);
        }else{//所有员工查询
            roleList = roleService.getPageRole(offset, limit, null);
            count = roleService.countAll(null);
        }
        //前台分页 json 格式
        map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", roleList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 跳转增加页面
     * @return
     */
    @RequestMapping("role/addRole.do")
    public  String addRole(){
        return "/jsp/system/role/addRole";
    }
    /**
     * 保存角色信息
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="role/saveRole.do", method=RequestMethod.POST)
    public String saveRole(Role role,final RedirectAttributes redirectAttributes) {
        Integer[] menuIds = null;
        String ids = role.getIds();
        if(null!=ids && ids!=""){
            String[] split = ids.substring(0, ids.length()-1).split(",");
            menuIds = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                menuIds[i]=Integer.valueOf(split[i]);
            }
        }
        roleService.saveRole(role,menuIds);
        redirectAttributes.addFlashAttribute("message", "成功建立角色！");
        return "redirect:getAll.do";
    }
    @RequestMapping(value="role/deleteRole.do", method=RequestMethod.GET)
    public String deleteRole(@RequestParam("id") int id,final RedirectAttributes redirectAttributes) {
        try {
            roleService.deleteRole(id);
            redirectAttributes.addFlashAttribute("message", "成功删除角色！");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "删除角色失败！");
        }
        return "redirect:getAll.do";
    }
    @RequestMapping("role/editRole.do")
    public  String editRole(@RequestParam("id") int id,HttpServletRequest request){
        Role role = roleService.selectRoleById(id);
        request.setAttribute("role", role);
        return "/jsp/system/role/editRole";
    }
    /**
     * 修改回显menu选中数据
     * @param response
     * @param request
     */
    @RequestMapping(value = "role/ajaxMenu.do", method = RequestMethod.POST)
    public void ajaxMenu(@RequestParam("id") int id,HttpServletResponse response,HttpServletRequest request) {
        List<Menu> list = roleService.selectMenuByRoleId(id);
        try {
            JsonUtils.convertListToJson(list, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * ztree形式回显 数据
     * @param id
     * @param response
     * @param request
     */
    @RequestMapping(value = "role/ajaxZtreeMenu.do", method = RequestMethod.POST)
    public void ajaxZtreeMenu(@RequestParam("id") int id,HttpServletResponse response,HttpServletRequest request) {
        List<ZtreeBean> list = roleService.selectZtreeMenuByRoleId(id);
        try {
            JsonUtils.convertListToJson(list, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 更新角色
     * @return
     */
    @RequestMapping(value = "role/updateRole.do", method = RequestMethod.POST)
    public String updateMenu(Role role,final RedirectAttributes redirectAttributes) {
        Integer[] menuIds = null;
        String ids = role.getIds();
        if(null!=ids && ids!=""){
            String[] split = ids.substring(0, ids.length()-1).split(",");
            menuIds = new Integer[split.length];
            for (int i = 0; i < split.length; i++) {
                menuIds[i]=Integer.valueOf(split[i]);
            }
        }
        roleService.updateRole(role, menuIds);
        redirectAttributes.addFlashAttribute("message", "成功更新角色！");
        return "redirect:getAll.do";
    }
    @RequestMapping("role/lookupRole.do")
    public  String lookupRole(@RequestParam("id") int id,HttpServletRequest request){
        Role role = roleService.selectRoleById(id);
        request.setAttribute("role", role);
        return "/jsp/system/role/lookupRole";
    }
}
