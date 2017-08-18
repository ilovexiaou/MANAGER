package com.system.template.process.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;















import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.template.process.bean.ProcessTemplate;
import com.system.template.process.bean.ProcessTemplateForm;
import com.system.template.process.bean.ProcessTemplateSub;
import com.system.template.process.mapper.ProcessTemplateMapper;
@Service
public class ProcessTemplateImpl implements ProcessTemplateService {
    @Resource
    ProcessTemplateMapper processTemplateMapper;
    
    @Override
    public List<ProcessTemplate> getPageProcess(int offset, int limit) {
        return processTemplateMapper.getPageProcess(offset, limit);
    }

    @Override
    public int countProcess() {
        return processTemplateMapper.countProcess();
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void saveProcess(ProcessTemplateForm ptForm) {
        ProcessTemplate pt = new ProcessTemplate();
        pt.setName(ptForm.getName());
        pt.setNumber(ptForm.getNumber());
        pt.setCreatedId(ptForm.getCreatedId());
        processTemplateMapper.saveProcess(pt);
        String[] names = ptForm.getNames();
        Integer[] levels = ptForm.getLevels();
        String[] auditIds = ptForm.getAuditIds();
        String[] auditSigns = ptForm.getAuditSigns();
        ProcessTemplateSub pts;
        for (int i = 0; i < names.length; i++) {
            pts = new ProcessTemplateSub();
            pts.setName(names[i]);
            pts.setLevel(levels[i]);
            pts.setAudit_sign(auditSigns[i]);
            pts.setTemplateId(pt.getId());
            if(auditSigns[i].equals("14")){//角色
                pts.setRoleId(Integer.valueOf(auditIds[i]));
            }else if(auditSigns[i].equals("15")){//人员
                pts.setEmployeeId(Integer.valueOf(auditIds[i]));
            }
            processTemplateMapper.saveProcessSub(pts);
        }
    }

    @Override
    public List<Object> getConditionProcess(int id) {
        ProcessTemplate pt = processTemplateMapper.getPageConditionProcess(id);
        List<ProcessTemplateSub> ptsList = processTemplateMapper.getPageConditionProcessSub(id);
        List<Object> list = new ArrayList<Object>(2);
        list.add(pt);
        list.add(ptsList);
        return list;
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void deleteProcess(int id) {
        processTemplateMapper.deleteProcess(id);
        processTemplateMapper.deleteProcessSub(id);
    }

}
