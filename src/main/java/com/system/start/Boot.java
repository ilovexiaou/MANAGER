package com.system.start;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.utils.quartz.bean.AutoTask;
import com.utils.quartz.mapper.AutoTaskMapper;
import com.utils.quartz.service.TaskManager;

@Service
public class Boot 
/*implements ApplicationContextAware,ServletContextAware 
,InitializingBean ,ApplicationListener<ContextRefreshedEvent> */ {
    
    /**
     * 自动任务 quartz
     */
    @Resource
    AutoTaskMapper autoTaskMapper;
    
    @PostConstruct
    public void applicationStart() {
        System.out.println("application start");
        List<AutoTask> list = autoTaskMapper.getAll();
        for (AutoTask autoTask : list) {
            Integer id = autoTask.getId();
            String name = autoTask.getName();
            String crom_time = autoTask.getCrom_time();
            Integer taskId = autoTask.getTaskId();
            String task_table = autoTask.getTask_table();
            Map<String, Object> contextMap = new HashMap<String, Object>();
            contextMap.put("id", id);
            contextMap.put("taskId", taskId);
            contextMap.put("task_table", task_table);
            TaskManager tm = TaskManager.getInstance();
            try {
                tm.addJob(name, null, crom_time, contextMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //缓存
        /*LoadingCache<Integer,String> studentCache
        //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
        = CacheBuilder.newBuilder()
        //设置并发级别为8，并发级别是指可以同时写缓存的线程数
        .concurrencyLevel(8)
        //设置写缓存后8秒钟过期
        .expireAfterWrite(8, TimeUnit.SECONDS)
        //设置缓存容器的初始容量为10
        .initialCapacity(10)
        //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
        .maximumSize(100)
        //设置要统计缓存的命中率
        .recordStats()
        //设置缓存的移除通知
        .removalListener(new RemovalListener<Object, Object>() {
            @Override
            public void onRemoval(RemovalNotification<Object, Object> notification) {
                System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
            }
        })
        //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
        .build(
                new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        System.out.println("load student " + key);
                        return "mySutdents";
                    }
                }
        );

        for (int i=0;i<20;i++) {
            //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
            String student;
            try {
                student = studentCache.get(1);
                System.out.println(student);
              //休眠1秒
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        System.out.println("cache stats:");
        //最后打印缓存的命中率等 情况
        System.out.println(studentCache.stats().toString());*/
    }


    /*@Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("1");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("2");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        // TODO Auto-generated method stub
        System.out.println("3");
    }


    @Autowired
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        System.out.println("4");
        List<AutoTask> list = autoTaskMapper.getAll();
        for (AutoTask autoTask : list) {
            Integer id = autoTask.getId();
            String name = autoTask.getName();
            String crom_time = autoTask.getCrom_time();
            Integer taskId = autoTask.getTaskId();
            String task_table = autoTask.getTask_table();
            TaskManager tm =TaskManager.getInstance();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", id);
            map.put("taskId", taskId);
            map.put("task_table", task_table);
            try {
                tm.addJob(name, null, crom_time, map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

        
}
