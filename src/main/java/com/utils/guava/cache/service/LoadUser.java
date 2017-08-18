package com.utils.guava.cache.service;

import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personnel.employee.bean.Employee;
import com.personnel.employee.service.EmployeeService;
import com.utils.guava.cache.GuavaAbstractLoadingCache;
import com.utils.guava.cache.ILocalCache;

@Service
public class LoadUser extends GuavaAbstractLoadingCache<Integer, Employee> implements ILocalCache<Integer , Employee>{
    @Resource
    EmployeeService employeeService;
    
    private LoadUser(){  
        setMaximumSize(300); //最大缓存条数  
        setExpireAfterWriteDuration(2);//过期时间 分钟
    }  
    
    @Override
    public Employee get(Integer key) {
        System.out.println("loadUser get工作中");
        try {
            return getValue(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    protected Employee fetchData(Integer key) {
        System.out.println("loadUser fetchData工作中");
        Employee employee = employeeService.selectEmployeeById(key);
        return employee;
    }

}
