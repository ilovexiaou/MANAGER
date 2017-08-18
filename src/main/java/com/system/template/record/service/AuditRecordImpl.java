package com.system.template.record.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.template.process.bean.ProcessTemplateSub;
import com.system.template.process.service.ProcessTemplateService;
import com.system.template.record.bean.AuditRecord;
import com.system.template.record.mapper.AuditRecordMapper;
import com.utils.random.RandomUtils;

@Service
public class AuditRecordImpl implements AuditRecordService,AuditRecordProcessService{
    @Resource
    ProcessTemplateService processTemplateService;
    @Resource
    AuditRecordMapper auditRecordMapper;
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    @Override
    public String createAuditRecord(AuditRecord ar) {
        String number = RandomUtils.getRamdom();
        // list 2条记录 第一条是总表 第二条是分表
        List<Object> list = processTemplateService.getConditionProcess(ar.getTemplateId());
        List<ProcessTemplateSub> ptsList = (List<ProcessTemplateSub>) list.get(1);
        int count = 0;
        for (ProcessTemplateSub pts : ptsList) {
            AuditRecord auditRecord = new AuditRecord();
            auditRecord.setBillId(ar.getBillId());
            auditRecord.setBillTable(ar.getBillTable());
            auditRecord.setTemplateId(ar.getTemplateId());
            auditRecord.setCreatedId(ar.getCreatedId());
            auditRecord.setNumber(number);
            
            auditRecord.setName(pts.getName());
            auditRecord.setLevel(pts.getLevel());
            auditRecord.setTemplate_subId(pts.getId());
            
            if(count == 0){
                auditRecord.setAudit_status("待审核");
                count++;
            }
            String audit_sign = pts.getAudit_sign();
            if(audit_sign.equals("15")){//员工
                auditRecord.setEmployeeId(pts.getEmployeeId());
            }else if(audit_sign.equals("14")){//角色
                auditRecord.setRoleId(pts.getRoleId());
            }else{//interface 接口
                
            }
            auditRecordMapper.saveAuditRecord(auditRecord);
        }
        return number;
    }

    @Override
    public void saveAuditRecord(AuditRecord auditRecord) {
        createAuditRecord(auditRecord);
    }

}
