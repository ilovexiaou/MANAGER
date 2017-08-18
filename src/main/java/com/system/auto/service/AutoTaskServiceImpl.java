package com.system.auto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utils.quartz.bean.AutoTask;
import com.utils.quartz.mapper.AutoTaskMapper;
import com.utils.quartz.service.TaskManager;

@Service
public class AutoTaskServiceImpl implements AutoTaskService {
    @Resource
    AutoTaskMapper autoTaskMapper;
    
    @Override
    public List<AutoTask> getAllbyPaging(int offset, int limit) {
        return autoTaskMapper.getAllbyPaging(offset, limit);
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void saveAutoTask(AutoTask autoTask) {
        autoTaskMapper.saveAutoTask(autoTask);
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

    @Override
    public int countAll() {
        return autoTaskMapper.countAll();
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void deleteAutoTask(int id) {
        AutoTask autoTask = autoTaskMapper.findAutoTask(id);
        String name = autoTask.getName();
        TaskManager tm = TaskManager.getInstance();
        try {
            tm.removeJob(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        autoTaskMapper.deleteAutoTask(id);
    }

}
