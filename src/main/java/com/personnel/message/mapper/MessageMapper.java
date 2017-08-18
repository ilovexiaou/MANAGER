package com.personnel.message.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.personnel.message.bean.Message;

public interface MessageMapper {
    public List<Message> getPageMessage(@Param("offset") int offset, @Param("limit") int limit);
    public int countAllMessage();
    
    public void saveMessage(Message message);
}
