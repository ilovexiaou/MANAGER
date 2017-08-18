package com.base.login.controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.constant.Constant;
import com.base.login.domain.FirstMenu;
import com.base.login.domain.SecondMenu;
import com.base.login.domain.ThirdMenu;
import com.system.menu.bean.Menu;
import com.system.user.service.UserService;
import com.utils.json.JsonUtils;
import com.utils.session.LoginSerssion;


@Controller
public class Login {
    @Resource
    UserService userService;
    
    
    /**
     * 登录
     * @param username
     * @param password
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping("login.do")
    public  String listAll(@RequestParam("username") String username, @RequestParam("password") String password,
            Model model,HttpSession httpSession){
            String strs = userService.isLogin(username, password);
            if(strs!=""){
                String[] split = strs.split(",");
                String employeeName = split[0];
                String employeeId = split[1];
                httpSession.setMaxInactiveInterval(2*60*60); //秒
                httpSession.setAttribute("china_name", employeeName);
                httpSession.setAttribute("china_id", employeeId);
                httpSession.setAttribute("username", username);
                if(checkSession(username,httpSession)){
                    //将原来登录session清除
                    LoginSerssion.userlogout(username);
                }
                LoginSerssion.mapSession.put(username, httpSession);
                Iterator<Entry<String, HttpSession>> iterator = LoginSerssion.mapSession.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, HttpSession> entry = iterator.next();
                    System.out.println(entry.getKey()+"___"+entry.getValue());
                }
                return "/jsp/index/main";
            }else{
                return "/jsp/index/error";
            }
    }
    /**
     * 刷新首页
     * @param httpSession
     * @return
     */
    @RequestMapping("reflash.do")
    public String ReFrash(HttpSession httpSession){
        Object china_name = httpSession.getAttribute("china_name");
        if("".equals(china_name) || null==china_name){
            return "/jsp/index/error";
        }
        return "/jsp/index/main";
    }
    /**
     * 退出登录
     * @param httpSession
     * @return
     */
    @RequestMapping("quitLogin.do")
    public String QuitLogin(HttpSession httpSession){
        String username = (String) httpSession.getAttribute("username");
        LoginSerssion.mapSession.remove(username);
        httpSession.invalidate();
        return "/jsp/index/login";
    }
    
    /**
     * 初始化菜单 json
     * @param response
     */
    @RequestMapping(value = "getMenu.do", method = RequestMethod.GET)  
    public void list(HttpServletResponse response,HttpSession httpSession) { 
        String obj = (String) httpSession.getAttribute("china_id");
        if(null!=obj && ""!=obj){
            Integer userId = Integer.valueOf(obj);
            List<Menu> menus = userService.selectMenu(userId);
            FirstMenu[] fmm = getMenuConstruction(menus);
        
        
        /*FirstMenu fm = new FirstMenu();
        SecondMenu sm1 = new SecondMenu();
        SecondMenu sm2 = new SecondMenu();
        ThirdMenu tm1=new ThirdMenu();
        ThirdMenu tm2=new ThirdMenu();
        
        tm1.setId("code");
        tm1.setText("aaaaaaaa1");
        tm1.setHref("user/getAll.do");
        
        tm2.setText("aaa22");
        tm2.setHref("employee/getAll.do");
        
        ThirdMenu[] tmm1= new ThirdMenu[2];
        ThirdMenu[] tmm2= new ThirdMenu[1];
        tmm1[0]=tm1;
        tmm1[1]=tm2;
        tmm2[0]=tm2;
        sm1.setId("b1");
        sm1.setCollapsed("true");
        sm1.setItems(tmm1);
        sm1.setText("第一个栏目");
        sm2.setId("b2");
        sm2.setCollapsed("true");
        sm2.setItems(tmm2);
        sm2.setText("第二个栏目");
        SecondMenu[] smm=new SecondMenu[2];
        smm[0]=sm1;
        smm[1]=sm2;
        fm.setId("menu");
        fm.setMenu(smm);
        fm.setText("首页测试版");
        fm.setPicture("nav-home");
        FirstMenu[] fmm= new FirstMenu[20];
        for(int i = 0 ; i<20 ;i++){
            if(i!=0){
                fm=new FirstMenu();
                fm.setId(String.valueOf(i));
                fm.setText("66"+i);
                fm.setPicture("nav-inventory");
            }
            fmm[i]=fm;
        }*/
            try {
                JsonUtils.convertListToJson(fmm, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
    
    private FirstMenu[] getMenuConstruction(List<Menu> menus){
        List<FirstMenu> firstList = new ArrayList<FirstMenu>();
        boolean flag = true;
        for (Menu menu : menus) {
            if(menu.get_parentId()==null){//第一级菜单
                FirstMenu fm= new FirstMenu();
                fm.setId(menu.getId().toString());
                fm.setText(menu.getName());
                fm.setPicture(menu.getPicture());
                if(flag)fm.setHomePage(Constant.MENU_CODE);
                fm.setMenu(getSecondChild(menus,menu.getId(),flag));
                flag=false;
                firstList.add(fm);
            }
        }
        return firstList.toArray(new FirstMenu[firstList.size()]);
    }
    
    private SecondMenu[] getSecondChild(List<Menu> menus,Integer parentId,boolean flag){
        List<SecondMenu> secondList = new ArrayList<SecondMenu>();
        for (Menu menu : menus) {
            if(menu.getLevel().equals("2") && menu.get_parentId()==parentId){//第一集菜单
                SecondMenu sm = new SecondMenu();
                sm.setText(menu.getName());
                sm.setCollapsed("true");
                sm.setItems(getThirdChild(menus,menu.getId(),flag));
                flag=false;
                secondList.add(sm);
            }
        }
        return secondList.toArray(new SecondMenu[secondList.size()]);
    }
    
    private ThirdMenu[] getThirdChild(List<Menu> menus,Integer parentId,boolean flag){
        List<ThirdMenu> thirdList = new ArrayList<ThirdMenu>();
        for (Menu menu : menus) {
            if(menu.getLevel().equals("3") && menu.get_parentId()==parentId){//第一集菜单
                ThirdMenu tm = new ThirdMenu();
                if(flag){
                    tm.setId("code");
                }else{
                    tm.setId(menu.getId().toString());
                }
                tm.setText(menu.getName());
                tm.setHref(menu.getHref());
                flag=false;
                thirdList.add(tm);
            }
        }
        return thirdList.toArray(new ThirdMenu[thirdList.size()]);
    }
    /**
     * 判断是否重复登录，重复登录清除原session返回false
     * @param userName
     * @return
     */
    private boolean checkSession(String userName,HttpSession httpSession){
        HttpSession session = LoginSerssion.mapSession.get(userName);
        boolean equals = httpSession.equals(session);
        if(null!=session && !equals){//session有值，判定重复登录
            return true;
        }
        return false;
    }
    /**
     * ajax 轮询查找是否登录
     * @param request
     * @param response
     */
    @RequestMapping("sendMessage")
    public void sendMessage (HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
        String uuid = request.getParameter("uuid");
        System.out.println(uuid);
        List<String> list = new ArrayList<String>();
        
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10000);
                System.out.println(i);
                Iterator<Entry<String, String>> iterator = LoginSerssion.phoneLoginMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, String> entry = iterator.next();
                    if(entry.getKey().equals(uuid)){
                        String strs = entry.getValue();
                        String[] split = strs.split(",");
                        String employeeName = split[0];
                        String employeeId = split[1];
                        httpSession.setMaxInactiveInterval(2*60*60); //秒
                        httpSession.setAttribute("china_name", employeeName);
                        httpSession.setAttribute("china_id", employeeId);
                        LoginSerssion.phoneLoginMap.remove(uuid);
                        list.add("scueess");
                        break;
                    }
                }
                if(list.size()>0)break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(list.size()==0)list.add("failure");
        try {
            JsonUtils.convertListToJson(list, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 手机扫码 跳转登录页面
     * @param request
     * @param response
     */
    @RequestMapping("loginrediectPhoneLogin")
    public String myPhoneLogin (HttpServletRequest request){
        String uuid = request.getParameter("uuid");
        request.setAttribute("uuid", uuid);
        return "/jsp/index/PhoneLogin";
    }
    /**
     * 手机端验证 用户名 密码
     * @return
     */
    @RequestMapping("loginPhoneLogin.do")
    public String PhoneLogin (HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String strs = userService.isLogin(username, password);
        if(strs!=""){
            String uuid = request.getParameter("uuid");
            LoginSerssion.phoneLoginMap.put(uuid, strs);
            return "/jsp/index/succes";
        }
        return "/jsp/index/error";
    }
}
