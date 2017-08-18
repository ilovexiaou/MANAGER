package com.utils.quartz.service;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.personnel.message.bean.Message;
import com.personnel.message.service.MessageService;

@Service
public class TaskJob  implements Job  {
    @Autowired  
    MessageService messageService;
    
    @Override
    public void execute(JobExecutionContext contextMap) throws JobExecutionException {
         SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        
         JobDataMap dataMap = contextMap.getJobDetail().getJobDataMap();        
         //参数集合
         //String ppt = dataMap.getString("ppt");
         //Integer id = dataMap.getInt("id");
         //Integer taskId = dataMap.getInt("taskId");
         String task_table = dataMap.getString("task_table");
         //写逻辑
         if(null!=task_table && task_table.equals("personnel_message")){
            Message message = new Message();
            message.setName("自动任务");
            message.setComment("每整点必发！！！！");
            message.setCreatedId(16);
            messageService.saveMessage(message);
         }
    }

}
