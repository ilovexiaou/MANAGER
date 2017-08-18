package com.personnel.vacation.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personnel.vacation.bean.Vacation;
import com.personnel.vacation.mapper.VacationMapper;
import com.system.template.process.bean.ProcessTemplateSub;
import com.system.template.record.bean.AuditRecord;
import com.system.template.record.service.AuditRecordService;
@Service
public class VacationServiceImpl implements VacationService {
    @Resource
    VacationMapper vacationMapper;
    @Resource
    AuditRecordService auditRecordService;
    
    @Override
    public List<Vacation> getMyPageVacation(int offset, int limit, int createdId) {
        return vacationMapper.getMyPageVacation(offset, limit, createdId);
    }

    @Override
    public int countMyPageVacation(int createdId) {
        return vacationMapper.countMyPageVacation(createdId);
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void saveVacation(Vacation vacation) {
        List<ProcessTemplateSub> list = vacationMapper.findTemplateProcess();
        Integer createdId = vacation.getCreatedId();
        vacation.setNameId(createdId);
        vacation.setAuditStatus("审核中");
        vacation.setAuditProcess(list.get(0).getName()+"审核中");
        
        vacationMapper.saveVacation(vacation);
        
        Integer id = vacation.getId();
        AuditRecord ar = new AuditRecord();
        ar.setBillId(id);
        ar.setBillTable("personnel_vaction");
        ar.setTemplateId(list.get(0).getTemplateId());
        ar.setCreatedId(createdId);
        auditRecordService.saveAuditRecord(ar);
        /*int count = 0;
        for (ProcessTemplateSub pts : list) {
            ar = new AuditRecord();
            ar.setBillId(id);
            ar.setBillTable("personnel_vaction");
            ar.setName(pts.getName());
            ar.setTemplateId(pts.getTemplateId());
            ar.setTemplate_subId(pts.getId());
            ar.setLevel(pts.getLevel());
            ar.setRoleId(pts.getRoleId());
            ar.setEmployeeId(pts.getEmployeeId());
            if(count==0){
                ar.setAudit_status("待审核");
                count++;
            }
            ar.setCreatedId(vacation.getCreatedId());
            ar.setCreateDateTime(vacation.getCreateDateTime());
            vacationMapper.saveAuditRecord(ar);
        }*/
    }

    @Override
    public List<Vacation> getApprovalVacation(int offset, int limit, int createdId) {
        return vacationMapper.getApprovalVacation(offset, limit, createdId);
    }

    @Override
    public int countApprovalVacation(int createdId) {
        return vacationMapper.countApprovalVacation(createdId);
    }

    @Override
    public List<Object> getApproval(int id) {
        List<Object> list = new ArrayList<Object>(2);
        Vacation va = vacationMapper.findVacation(id);
        List<ProcessTemplateSub> record = vacationMapper.findRecord(id, "personnel_vaction");
        list.add(va);
        list.add(record);
        return list;
    }

    @Override
    @Transactional(readOnly = false,  rollbackFor = Exception.class)
    public void updateApprovalVacation(AuditRecord auditRecord) {
        AuditRecord ar = vacationMapper.findAuditRecord(auditRecord.getBillId(), "personnel_vaction");
        vacationMapper.updateApprovalVacation(auditRecord);
        int level = ar.getLevel();
        List<ProcessTemplateSub> list = vacationMapper.findTemplateProcess();
        list.size();
        if(level==list.size()){
            vacationMapper.updateVacation(auditRecord.getBillId(),auditRecord.getAudit_result(),"已审核");
        }else{
            if(auditRecord.getAudit_result().equals("通过")){
                List<ProcessTemplateSub> ptList = vacationMapper.findTemplateProcess();
                vacationMapper.updateVacation(auditRecord.getBillId(), ptList.get(level).getName()+"审核中","审核中");
                vacationMapper.updateApprovalNext(auditRecord.getBillId(), "personnel_vaction", level+1);
            }else{//不通过
                vacationMapper.updateVacation(auditRecord.getBillId(), "不通过","已审核");
            }
        }
    }

}
