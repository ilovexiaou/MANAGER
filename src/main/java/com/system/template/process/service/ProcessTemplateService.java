package com.system.template.process.service;

import java.util.List;



import com.system.template.process.bean.ProcessTemplate;
import com.system.template.process.bean.ProcessTemplateForm;

public interface ProcessTemplateService {
    public List<ProcessTemplate> getPageProcess(int offset,  int limit);
    public int countProcess();
    public void saveProcess(ProcessTemplateForm processTemplateForm);
    public List<Object> getConditionProcess(int id);
    public void deleteProcess(int id);
}
