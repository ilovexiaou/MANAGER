package com.system.template.record.mapper;

import com.system.template.record.bean.AuditRecord;

public interface AuditRecordMapper {
    /**
     * 生成审批流
     * @param auditRecord
     * 其中 billId billTable templateId createdId 为必填
     */
    public void saveAuditRecord(AuditRecord auditRecord);
}
