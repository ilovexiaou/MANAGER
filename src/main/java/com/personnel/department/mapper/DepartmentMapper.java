package com.personnel.department.mapper;

import java.util.List;

import com.personnel.department.bean.Department;

public interface DepartmentMapper {
    /**
     * 得到所有的部门
     * @return
     */
    public List<Department> getAll();
    /**
     * 用户选择部门，选择所有可用的部门
     * @return
     */
    public List<Department> getSelect();
    /**
     * 新加一个部门
     * @param department
     * @return
     */
    public void addDepartment(Department department);
    /**
     * 根据id删除部门
     * @param id
     */
    public void deleteDepartment(Integer id);
    /**
     * 更新一个部门
     * @param department
     */
    public void updateDepartment(Department department);
}
