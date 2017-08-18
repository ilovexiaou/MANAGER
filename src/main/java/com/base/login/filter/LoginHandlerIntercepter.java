package com.base.login.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginHandlerIntercepter implements HandlerInterceptor{

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
    /**
     * 登录拦截器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse respose, Object arg2) throws Exception {
            //读取请求地址
            //String requestURI = request.getRequestURI();  
            //说明处在编辑的页面  
            HttpSession session = request.getSession();  
            String username = (String) session.getAttribute("china_name");  
            if(username!=null){  
                //登陆成功的用户  
                return true;  
            }else{  
               //没有登陆，转向登陆界面  
              request.getRequestDispatcher("/jsp/index/login.jsp").forward(request,respose);  
              return true;  
            }  
    }

}
