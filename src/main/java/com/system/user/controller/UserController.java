package com.system.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.system.role.bean.Role;
import com.system.user.bean.User;
import com.system.user.service.UserService;
import com.utils.json.JsonUtils;

@Controller
public class UserController {
    @Resource
    UserService userService;
    
    @RequestMapping("user/getAll.do")
    public  String redirect(Model model){
        return "/jsp/system/user/showUser";
    }
    
    @RequestMapping(value="user/ajax.do", method = RequestMethod.POST)
    public void showAll(HttpServletResponse response,HttpServletRequest request){
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        List<User> userList = userService.getAll(offset,limit);
        int count = userService.countAll();
        //前台分页 json 格式
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", userList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("user/addUser.do")
    public  String adduser(Model model,HttpServletRequest request){
        List<Role> roles = userService.getRoleAll();
        request.setAttribute("roles", roles);
        return "/jsp/system/user/addUser";
    }
    
    @RequestMapping(value="user/saveUser.do", method = RequestMethod.POST)
    public String saveUser(User user,final RedirectAttributes redirectAttributes){
        try{
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message", "成功新建用户！");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "新建用户失败！");
        }
        
        return "redirect:getAll.do";
    }
    
    @RequestMapping(value="user/ajaxByCondition.do", method = RequestMethod.POST)
    public void selectUserByCondition(HttpServletResponse response,HttpServletRequest request){
        String rows = request.getParameter("rows");//一页显示几个
        String page = request.getParameter("page");//第几页
        
        String userName = request.getParameter("userName");
        String employeeName = request.getParameter("employeeName");
        String flag = request.getParameter("flag");
        //查询几个
        Integer limit=Integer.valueOf(rows);
        //从第几个开始查，0是第一个
        Integer offset=(Integer.valueOf(page)-1)*limit;
        
        boolean bflag ;
        if(flag.equals("0"))bflag=false;
        else bflag=true;
        
        List<User> userList = userService.selectUserByCondition(userName,employeeName,bflag, offset, limit);
        int count = userService.countByCondition(userName,employeeName,bflag);
        //前台分页 json 格式
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("total", count);
        map.put("rows", userList);
        try {
            JsonUtils.convertListToJson(map, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
