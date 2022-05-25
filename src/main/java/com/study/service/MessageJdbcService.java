package com.study.service;

import com.study.repository.MessageJdbcRepository;
import com.study.vo.Message;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageJdbcService {
    private final MessageJdbcRepository messageJdbcRepository;

    public Message save(String text) {
        return messageJdbcRepository.saveMessage(new Message(text));
    }
}
