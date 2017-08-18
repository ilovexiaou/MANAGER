package com.system.auto.service;

import java.util.List;

import com.utils.quartz.bean.AutoTask;

public interface AutoTaskService {
    public List<AutoTask> getAllbyPaging(int offest,int limit);
    public void saveAutoTask(AutoTask autoTask);
    public int countAll();
    public void deleteAutoTask(int id);
}
