package com.study.repository;

import javax.sql.DataSource;

import com.study.vo.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MessageJdbcTemplateRepository {
    
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MessageJdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Message saveMessage(Message message) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("text", message.getText());
        params.addValue("createdData", message.getCreatedDate());

        String insertSql = "INSERT INTO messages(id, text, created_date) VALUES(null, :text, :createdDate)";
        
        try {
            this.jdbcTemplate.update(insertSql, params, holder);
        } catch (DataAccessException e) {
            log.error("Failed to save message", e);
            return null;
        }
        return new Message(holder.getKey().intValue(), message.getText(), message.getCreatedDate());
    }
}
