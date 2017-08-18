package com.personnel.employee.service;

import java.util.HashMap;
import java.util.List;

import com.personnel.employee.bean.Employee;

public interface EmployeeService {
    /**
     * 列出所有员工
     * @return
     */
    public List<Employee> getPagingAll(int offset,int limit,Integer flag);
    public int countAllEmployee(Integer flag);
    public List<Employee> selectConditionEmployee(HashMap<String, Object> map);
    public int countConditionEmployee(HashMap<String, Object> map);
    public void saveEmployee(Employee employee);
    public void deleteEmployee(int id);
    public Employee selectEmployeeById(int id);
    public void updateEmployee(Employee employee);
}
