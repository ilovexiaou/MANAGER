package com.system.template.process.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.template.process.bean.ProcessTemplate;
import com.system.template.process.bean.ProcessTemplateSub;

public interface ProcessTemplateMapper {
    /*
     * 得到审批流程模板
     */
    public List<ProcessTemplate> getPageProcess(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 计算总页数
     * @return
     */
    public int countProcess();
    
    public void saveProcess(ProcessTemplate processTemplate);
    public void saveProcessSub(ProcessTemplateSub processTemplateSub);
    
    public ProcessTemplate getPageConditionProcess(@Param("id") int id);
    public List<ProcessTemplateSub> getPageConditionProcessSub(@Param("templateId") int templateId);

    public void deleteProcess(@Param("id") int id);
    public void deleteProcessSub(@Param("templateId") int templateId);
}
