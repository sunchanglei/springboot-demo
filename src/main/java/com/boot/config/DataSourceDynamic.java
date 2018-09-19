package com.boot.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（需要继承AbstractRoutingDataSource）
 * Created by yunfan on 2018/7/23.
 */
public class DataSourceDynamic extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return DatasSourceHolder.getDatabaseType();
    }
}
