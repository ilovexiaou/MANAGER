package com.utils.quartz.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;


public class TaskManager {
    private static  TaskManager instance ;
    private  SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
    
    private TaskManager(){};
    
    public static TaskManager getInstance(){
        if(instance == null) {
            synchronized(TaskManager.class){
                if(instance == null)instance = new TaskManager();
            }
        }
        return instance;
    }
    //添加job
    //两种传参数方式，一个是放在String ppt然后加载，一个是放在map里
    public void addJob(String jobName,String ppt,String cronExpression,Map<String,Object> contextMap) throws Exception{
        Scheduler sched = schedFact.getScheduler(); 
        if(!sched.isStarted()) sched.start();
        JobDetail jobDetail = newJob(TaskJob.class)
                .withIdentity(jobName, Scheduler.DEFAULT_GROUP)
                .usingJobData("ppt", ppt)
                .build();
        for(Object key : contextMap.keySet()){
            jobDetail.getJobDataMap().put(key.toString(), contextMap.get(key));
        }
        Trigger trigger = newTrigger()
                .withIdentity(jobName+"Trigger", Scheduler.DEFAULT_GROUP)
                .withSchedule(cronSchedule(cronExpression))
                .forJob(jobDetail)
                .build();
        sched.scheduleJob(jobDetail, trigger);
    }
    //添加job
    public void addJob(String jobName,String serviceName,String cronExpression) throws Exception{
        Scheduler sched = schedFact.getScheduler(); 
        //if(sched.isStarted()) sched.shutdown();
        if(!sched.isStarted()) sched.start();
        JobDetail jobDetail = newJob(TaskJob.class)
                .withIdentity(jobName, Scheduler.DEFAULT_GROUP)
                .usingJobData("serviceName", serviceName)
                .build();
        Trigger trigger = newTrigger()
                .withIdentity(jobName+"Trigger", Scheduler.DEFAULT_GROUP)
                .withSchedule(cronSchedule(cronExpression))
                .forJob(jobDetail)
                .build();
        sched.scheduleJob(jobDetail, trigger);
       
    }
    //移除
    public void removeJob(String jobName) throws Exception{
        Scheduler sched = schedFact.getScheduler(); 
        
        if(!sched.isStarted()) sched.start();
        sched.unscheduleJob(triggerKey(jobName + "Trigger", Scheduler.DEFAULT_GROUP));
        sched.deleteJob(jobKey(jobName, Scheduler.DEFAULT_GROUP));
        
        
    }
}
