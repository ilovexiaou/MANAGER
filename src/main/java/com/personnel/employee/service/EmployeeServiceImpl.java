package com.personnel.employee.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personnel.employee.bean.Employee;
import com.personnel.employee.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Resource
    EmployeeMapper employeeMapper;
    
    @Override
    public List<Employee> getPagingAll(int offset,int limit,Integer flag) {
        return employeeMapper.getPagingAll(offset,limit,flag);
    }
    
    @Override
    public int countAllEmployee(Integer flag) {
        return employeeMapper.countAllEmployee(flag);
    }

    @Override
    public List<Employee> selectConditionEmployee(HashMap<String, Object> map) {
        return employeeMapper.selectConditionEmployee(map);
    }

    @Override
    public int countConditionEmployee(HashMap<String, Object> map) {
        return employeeMapper.countConditionEmployee(map);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeMapper.saveEmployee(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeMapper.deleteEmployee(id);
    }

    @Override
    public Employee selectEmployeeById(int id) {
        System.out.println("加载原始数据中 employee!!!!");
        return employeeMapper.selectEmployeeById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

}
