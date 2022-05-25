package com.study.controller;

import com.study.dto.MessageData;
import com.study.service.MessageJdbcService;
import com.study.vo.Message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageJdbcController {
    
    private final MessageJdbcService messageJdbcService;

    @PostMapping("/jdbc/messages")
    public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
        Message saved = messageJdbcService.save(data.getText());
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(saved);
    }
}
