package com.personnel.message.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.personnel.message.bean.Message;
import com.personnel.message.mapper.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageMapper messageMapper;
    @Override
    public List<Message> getPageMessage(int offset, int limit) {
        return messageMapper.getPageMessage(offset, limit);
    }

    @Override
    public int countAllMessage() {
        return messageMapper.countAllMessage();
    }

    @Override
    public void saveMessage(Message message) {
        messageMapper.saveMessage(message);
    }

}
