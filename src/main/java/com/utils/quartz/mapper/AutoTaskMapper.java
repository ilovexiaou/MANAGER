package com.utils.quartz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.utils.quartz.bean.AutoTask;

public interface AutoTaskMapper {
    /**
     * 初始化所有的自动任务
     * @return
     */
    public List<AutoTask> getAll();
    /**
     * 分页显示自动任务
     * @param offset
     * @param limit
     * @return
     */
    public List<AutoTask> getAllbyPaging(@Param("offset") int offset, @Param("limit") int limit);
    public int countAll();
    public void saveAutoTask(AutoTask autoTask);
    public void deleteAutoTask(@Param("id") int id);
    public AutoTask findAutoTask(@Param("id") int id);
}
