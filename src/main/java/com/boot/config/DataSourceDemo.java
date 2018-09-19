package com.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceDemo {

    @Bean(name = "mysqlYhDb")
    @ConfigurationProperties(prefix = "spring.datasource.ds-yh")
    public javax.sql.DataSource yhDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlYsDb")
    @ConfigurationProperties(prefix = "spring.datasource.ds-ys")
    public javax.sql.DataSource ysDataSource() {
        return DataSourceBuilder.create().build();
    }
}
