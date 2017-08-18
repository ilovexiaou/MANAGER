package com.system.role.service;

import java.util.List;

import com.system.menu.bean.Menu;
import com.system.role.bean.Role;
import com.utils.bean.ZtreeBean;

public interface RoleService {
    public List<Role> getPageRole(int offset,int limit,Boolean countflag);
    public int countAll(Boolean countflag);
    public void saveRole(Role role,Integer[] ids);
    public void deleteRole(Integer id);
    public Role selectRoleById(Integer id);
    public List<Menu> selectMenuByRoleId(Integer roleId);
    public List<ZtreeBean> selectZtreeMenuByRoleId(Integer roleId);
    public void updateRole(Role role,Integer[] ids);
}
