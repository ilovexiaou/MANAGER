package com.personnel.employee.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.personnel.employee.bean.Employee;

public interface EmployeeMapper {
    /**
     * 列出所有员工
     * @return
     */
    public List<Employee> getPagingAll(@Param("offset") int offset, @Param("limit") int limit,@Param("flag") Integer flag);  
    /**
     * 计算所有员工数量
     * @return
     */
    public int countAllEmployee(@Param("flag") Integer flag);
    /**
     * 根据map查询符合条件的employee
     * @param map
     * @return
     */
    public List<Employee> selectConditionEmployee(HashMap<String, Object> map);
    /**
     * 计算符合条件的员工数量
     * @param map
     * @return
     */
    public int countConditionEmployee(HashMap<String, Object> map);
    /**
     * 保存员工
     * @param employee
     */
    public void saveEmployee(Employee employee);
    /**
     * 根据id删除员工
     * @param id
     */
    public void deleteEmployee(int id);
    /**
     * 根据id查找员工
     * @param id
     * @return
     */
    public Employee selectEmployeeById(int id);
    /**
     * 修改员工
     * @param employee
     */
    public void updateEmployee(Employee employee);
}
