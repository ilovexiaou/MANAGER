package com.system.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.system.menu.bean.Menu;
import com.system.menu.mapper.MenuMapper;

@Service
public class MenuServiceImpl implements MenuService{
    @Resource
    MenuMapper menuMapper;
    @Override
    public List<Menu> getAll(Integer flag) {
        return menuMapper.getAll(flag);
    }
    @Override
    public void addMenu(Menu Menu) {
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
            menuMapper.addMenu(Menu);
        } catch (Exception e) {
            transactionManager.rollback(status);
        } finally {     
            transactionManager.commit(status);   
        }
    }
    @Override
    public void updateMenu(Menu Menu) {
        menuMapper.updateMenu(Menu);
    }
    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteMenu(id);
    }
    @Override
    public List<Menu> getTreeMenu() {
        return menuMapper.getTreeMenu();
    }

}
