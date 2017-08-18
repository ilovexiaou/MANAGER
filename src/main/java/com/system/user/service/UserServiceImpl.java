package com.system.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.menu.bean.Menu;
import com.system.role.bean.Role;
import com.system.user.bean.User;
import com.system.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    UserMapper userMapper;
    @Override
    public List<User> getAll(int offset,int limit) {
        return userMapper.getAll(offset, limit);
    }
    @Override
    public int countAll() {
        return userMapper.countAll();
    }
    @Override
    public List<Role> getRoleAll() {
        return userMapper.getRoleAll();
    }
    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void saveUser(User user) {
        userMapper.saveUser(user);
        Integer userId = user.getId();
        userMapper.saveUserEmployee(userId, user.getEmployeeId());
        Integer[] roleIds = user.getRoleIds();
        for (Integer roleId : roleIds) {
            userMapper.saveUserRole(userId, roleId);
        }
    }
    @Override
    public String isLogin(String userName, String passWord) {
        User user = userMapper.selectUserLogin(userName, passWord);
        if(null==user){
            return "";
        }else{
            return user.getEmployeeName()+","+user.getId();
        }
    }
    @Override
    public List<Menu> selectMenu(Integer userId) {
        return userMapper.selectMenu(userId);
    }
    @Override
    public List<User> selectUserByCondition(String userName, String employeeName, boolean flag, int offset, int limit) {
        return userMapper.selectUserByCondition(userName, employeeName, flag, offset, limit);
    }
    @Override
    public int countByCondition(String userName, String employeeName, boolean flag) {
        return userMapper.countByCondition(userName, employeeName, flag);
    }
    
}
