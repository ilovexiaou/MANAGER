package com.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.menu.bean.Menu;
import com.system.role.bean.Role;
import com.system.user.bean.User;

public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    public List<User> getAll(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 计算所有用户数量
     * @return
     */
    public int countAll();
    /**
     * 得到所有的权限
     * @return
     */
    public List<Role> getRoleAll();
    /**
     * 保存用户
     * @param user
     */
    public void saveUser(User user);
    /**
     * 保存用户员工关联表
     * @param userId
     * @param employeeId
     */
    public void saveUserEmployee(@Param("userId") Integer userId,@Param("employeeId") Integer employeeId);
    /**
     * 保存用户角色关联表
     * @param userId
     * @param roleId
     */
    public void saveUserRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
    /**
     * 用户登录
     */
    public User selectUserLogin(@Param("userName") String userName,@Param("passWord") String passWord);
    /**
     * 根据用户id，搜索出对应角色对应的菜单
     * @return
     */
    public List<Menu> selectMenu(@Param("userId") Integer userId);
    /**
     * 搜索栏 里搜索
     * @return
     */
    public List<User> selectUserByCondition(@Param("userName") String userName,@Param("employeeName") String employeeName,@Param("flag") boolean flag,@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 搜索栏 里计算分页总数
     * @param user
     * @return
     */
    public int countByCondition(@Param("userName") String userName,@Param("employeeName") String employeeName,@Param("flag") boolean flag);
}
