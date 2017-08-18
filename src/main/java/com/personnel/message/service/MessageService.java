package com.personnel.message.service;

import java.util.List;

import com.personnel.message.bean.Message;

public interface MessageService {
    public List<Message> getPageMessage(int offset, int limit);
    public int countAllMessage();
    
    public void saveMessage(Message message);
}
