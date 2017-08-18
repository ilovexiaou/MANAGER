package com.system.role.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.menu.bean.Menu;
import com.system.role.bean.Role;
import com.system.role.mapper.RoleMapper;
import com.utils.bean.ZtreeBean;
@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> getPageRole(int offset, int limit, Boolean countflag) {
        return roleMapper.getPageRole(offset, limit, countflag);
    }

    @Override
    public int countAll(Boolean countflag) {
        return roleMapper.countAll(countflag);
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void saveRole(Role role,Integer[] ids) {
        roleMapper.addRole(role);
        for (Integer menuId : ids) {
            roleMapper.addRoleMenu(role.getId(), menuId);
        }
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        roleMapper.deleteRole(id);
        roleMapper.deleteRoleMenu(id);
    }

    @Override
    public Role selectRoleById(Integer id) {
        return roleMapper.selectRoleById(id);
    }

    @Override
    public List<Menu> selectMenuByRoleId(Integer roleId) {
        return roleMapper.selectMenuByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void updateRole(Role role, Integer[] ids) {
        roleMapper.updateRole(role);
        Integer roleId = role.getId();
        List<Menu> list = selectMenuByRoleId(roleId);
        ArrayList<Integer> idsList = new ArrayList<Integer>(Arrays.asList(ids));
        boolean flag;
        for (Menu menu : list) {
            Integer menuId = menu.getId();
            flag = idsList.contains(menuId);
            //更新前的菜单不在更新后的菜单中，删除
            if(!flag){
                roleMapper.deleteRoleMenuByMenu(roleId, menuId);
            }
        }
        
        List<Integer> menuList = new ArrayList<Integer>();
        for (Menu menu : list) {
            menuList.add(menu.getId());
        }
        for (Integer id : ids) {
            flag = menuList.contains(id);
            //不在更新前的菜单里,添加
            if(!flag){
                roleMapper.addRoleMenu(roleId, id);
            }
        }
    }

    @Override
    public List<ZtreeBean> selectZtreeMenuByRoleId(Integer roleId) {
        return roleMapper.selectZtreeMenuByRoleId(roleId);
    }
}
