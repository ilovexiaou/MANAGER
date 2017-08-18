package com.utils.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

public class LoginSerssion {
    /**
     * 存放登录标识， map里key为用户名 value为对应的登录session
     */
    public static Map<String, HttpSession> mapSession = new ConcurrentHashMap<String, HttpSession>();
    public static Map<String,String> phoneLoginMap = new ConcurrentHashMap<String,String>();
    
    
    public static void userlogout(String userName){
        if(mapSession.get(userName)!=null){
          //得到需要退出的用户的session 
            HttpSession session = mapSession.get(userName); 
            //在map<username,session>中移除该用户，记住想要退出该用户，必须将该session废除或是remove掉user 
            mapSession.remove(userName); 
            //得到session的所属性合集 
            /*@SuppressWarnings("rawtypes")
            Enumeration e = session.getAttributeNames(); 
            //删除所有属性 
            while(e.hasMoreElements()){ 
              String sessionName = (String) e.nextElement(); 
              session.removeAttribute(sessionName); 
            } */
            //废除该session 
            try {
                session.invalidate(); 
            } catch (Exception e) {
                System.out.println("session"+session+"已过期");
            }
            
        }
    }
}
