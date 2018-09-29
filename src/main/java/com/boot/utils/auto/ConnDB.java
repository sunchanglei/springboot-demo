package com.boot.utils.auto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConnDB {

    //驱动
    private String driver;
    //数据库地址
    private String url;

    //密码
    private String schema;

    //用户名
    private String name;
    //密码
    private String passWord;
}
