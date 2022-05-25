package com.study.repository;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class MessageJdbcRepository {
    
    private final DataSource dataSource;

    public MessageJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    

}
