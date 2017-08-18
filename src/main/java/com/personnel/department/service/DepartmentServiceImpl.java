package com.personnel.department.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.personnel.department.bean.Department;
import com.personnel.department.mapper.DepartmentMapper;
@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Resource
    DepartmentMapper departmentMapper;
    
    @Override
    public List<Department> getAll() {
        return departmentMapper.getAll();
    }
    @Override
    public void addDepartment(Department department) {
        //获取Spring容器的对象
        WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
        //1.获取事务控制管理器
        DataSourceTransactionManager transactionManager = contextLoader.getBean(
        "transactionManager", DataSourceTransactionManager.class);
        //2.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //3.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //4.获得事务状态
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            departmentMapper.addDepartment(department);
            transactionManager.commit(status);   
        } catch (Exception e) {
            transactionManager.rollback(status);
        } 
    }
    @Override
    public void deleteDepartment(Integer id) {
        departmentMapper.deleteDepartment(id);
    }
    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
    }
    @Override
    public List<Department> getSelect() {
        return departmentMapper.getSelect();
    }

}
