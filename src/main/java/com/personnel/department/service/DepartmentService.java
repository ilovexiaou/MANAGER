package com.personnel.department.service;

import java.util.List;

import com.personnel.department.bean.Department;

public interface DepartmentService {
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
    public void addDepartment(Department department);
    public void deleteDepartment(Integer id);
    public void updateDepartment(Department department);
}
