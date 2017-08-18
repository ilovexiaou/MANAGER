package com.system.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.menu.bean.Menu;
import com.system.role.bean.Role;
import com.utils.bean.ZtreeBean;

public interface RoleMapper {
    /**
     * 分页显示角色信息
     * @param offset
     * @param limit
     * @return
     */
    public List<Role> getPageRole(@Param("offset") int offset, @Param("limit") int limit,@Param("countflag") Boolean countflag);
    /**
     * 计算角色数量
     * @return
     */
    public int countAll(@Param("countflag") Boolean countflag);
    /**
     * 添加角色，菜单对应信息
     */
    public void addRoleMenu(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId);
    /**
     * 添加角色信息
     * @param menu
     */
    public void addRole(Role role);
    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(@Param("id") Integer id);
    /**
     * 删除所有角色菜单关联信息
     * @param roleId
     */
    public void deleteRoleMenu(@Param("roleId") Integer roleId);
    /**
     * 更新时，删除对应的菜单信息
     */
    public void deleteRoleMenuByMenu(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId);
    /**
     * 根据id查出角色
     * @param id
     * @return
     */
    public Role selectRoleById(@Param("id") Integer id);
    /**
     * 根据角色id 查出对应的菜单
     * @param roleId
     * @return
     */
    public List<Menu> selectMenuByRoleId(@Param("roleId") Integer roleId);
    /**
     * 与selectMenuByRoleId功能一样，只不过输出形式不同
     * @param roleId
     * @return
     */
    public List<ZtreeBean> selectZtreeMenuByRoleId(@Param("roleId") Integer roleId);
    /**
     * 更新角色
     * @param role
     */
    public void updateRole(Role role);
}
