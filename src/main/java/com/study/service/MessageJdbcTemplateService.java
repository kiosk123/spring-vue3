package com.study.service;

import com.study.repository.MessageJdbcTemplateRepository;
import com.study.vo.Message;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageJdbcTemplateService {
    
    private final MessageJdbcTemplateRepository messageJdbcTemplateRepository;

    public Message save(String text) {
        return messageJdbcTemplateRepository.saveMessage(new Message(text));
    }
}
