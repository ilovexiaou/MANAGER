package com.personnel.vacation.service;

import java.util.List;

import com.personnel.vacation.bean.Vacation;
import com.system.template.record.bean.AuditRecord;


public interface VacationService {
    public List<Vacation> getMyPageVacation(int offset, int limit,  int createdId);
    public int countMyPageVacation( int createdId);
    public void saveVacation(Vacation vacation);
    
    public List<Vacation> getApprovalVacation(int offset, int limit,  int createdId);
    public int countApprovalVacation(int createdId);
    
    public List<Object> getApproval(int id);
    public void updateApprovalVacation(AuditRecord auditRecord);
}
