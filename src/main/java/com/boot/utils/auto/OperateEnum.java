package com.boot.utils.auto;

/**
 * Created by YScredit on 2018/10/19.
 */
public enum OperateEnum {

    COUNT("count","统计数量"),
    QUERY("query","查询单条信息"),
    LIST("list","查询列表信息"),
    PAGE("page","查询分页信息"),
    UPDATE("update","更新信息"),
    INSERT("insert","插入信息"),
    DELETE("delete","删除信息"),
    VALIDATE("validate","验证信息"),
    ANALYSIS("analysis","解析数据"),
    TASK("task","定时任务"),
    TO("to","转换数据"),
    THREAD("thread","线程"),
    LOGIN("login","登录"),
    REGISTER("register","注册");

    private String code;
    private String desc;

    OperateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
