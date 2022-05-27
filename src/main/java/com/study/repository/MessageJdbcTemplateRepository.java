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
        params.addValue("createdDate", message.getCreatedDate());

        String insertSql = "INSERT INTO messages(id, text, created_date) VALUES(null, :text, :createdDate)";
        
        try {
            this.jdbcTemplate.update(insertSql, params, holder);
        } catch (DataAccessException e) {
            log.error("Failed to save message", e);
            return null;
        }

        /**
         * The getKey method should only be used when a single key is returned. 
         * The current key entry contains multiple keys: [{ID=6, CREATED_DATE=2022-05-25 14:23:40.359}]] with root cause
         * 에러 발생대문에 추가한 로직...
         */
        int generatedKeyValue = holder.getKeyList().stream()
            .filter(map -> map.keySet().iterator().next().equalsIgnoreCase("ID"))
            .mapToInt(map -> (Integer)map.get("ID"))
            .sum();

        log.warn("holder's key value is {}", generatedKeyValue);
        return new Message(generatedKeyValue, message.getText(), message.getCreatedDate());
    }
}
