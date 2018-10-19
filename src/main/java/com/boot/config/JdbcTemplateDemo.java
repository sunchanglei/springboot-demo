package com.boot.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by yunfan on 2018/7/20.
 */
@Configuration
public class JdbcTemplateDemo {

    @Bean(name = "yhJdbcTemplate")
    public JdbcTemplate yhJdbcTemplate(@Qualifier("mysqlYhDb") DataSource dsMySQL) {
        return new JdbcTemplate(dsMySQL);
    }
    @Bean(name = "ysJdbcTemplate")
    public JdbcTemplate ysJdbcTemplate(@Qualifier("mysqlYsDb") DataSource dsMySQL) {
        return new JdbcTemplate(dsMySQL);
    }

}
