package com.boot.config;

/**
 *
 * 保存一个线程安全的DatabaseType容器
 * Created by yunfan on 2018/7/23.
 */
public class DatasSourceHolder {

    private static final ThreadLocal<DataSourceEnum> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DataSourceEnum type){
        contextHolder.set(type);
    }

    public static DataSourceEnum getDatabaseType(){
        return contextHolder.get();
    }
}
