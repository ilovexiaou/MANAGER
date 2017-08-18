package com.system.user.service;

import java.util.List;


import com.system.menu.bean.Menu;
import com.system.role.bean.Role;
import com.system.user.bean.User;

public interface UserService {
    public List<User> getAll(int offset,int limit);
    public int countAll();
    public List<Role> getRoleAll();
    public void saveUser(User user);
    /**
     * 判断是否登录，如果登录，返回用户名，如果错误，返回空字符串
     * @param userName
     * @param passWord
     * @return
     */
    public String isLogin(String userName, String passWord);
    public List<Menu> selectMenu(Integer userId);
    public List<User> selectUserByCondition(String userName,String employeeName,boolean flag,int offset,int limit);
    public int countByCondition(String userName,String employeeName,boolean flag);
}
