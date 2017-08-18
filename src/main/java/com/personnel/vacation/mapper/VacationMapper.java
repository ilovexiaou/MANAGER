package com.personnel.vacation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.personnel.vacation.bean.Vacation;
import com.system.template.process.bean.ProcessTemplateSub;
import com.system.template.record.bean.AuditRecord;

public interface VacationMapper {
    public List<Vacation> getMyPageVacation(@Param("offset") int offset, @Param("limit") int limit, @Param("createdId") int createdId);
    public int countMyPageVacation(@Param("createdId") int createdId);
    
    public void saveVacation(Vacation vacation);
    public void saveAuditRecord(AuditRecord auditRecord);
    /**
     * 查找 请假流程 的模板
     * @return id 流程模板主表id
     */
    public List<ProcessTemplateSub> findTemplateProcess();
    
    public List<Vacation> getApprovalVacation(@Param("offset") int offset, @Param("limit") int limit, @Param("createdId") int createdId);
    public int countApprovalVacation(@Param("createdId") int createdId);
    
    public Vacation findVacation(@Param("id") int id);
    public List<ProcessTemplateSub> findRecord(@Param("billId") int billId,@Param("billTable") String billTable);
    
    public void updateApprovalVacation(AuditRecord auditRecord);
    public AuditRecord findAuditRecord(@Param("billId") int billId,@Param("billTable") String billTable);
    public void updateVacation(@Param("id") int id,@Param("result") String result,@Param("status") String status);
    public void updateApprovalNext(@Param("billId") int billId,@Param("billTable") String billTable,@Param("level") int level);
    
}
