package com.boot.exception;

public enum BizErrEnum {

    SEARCH_FAILED("0002", "查询失败"),
    REQUEST_DUPLICATE("0003", "短时间内不允许相同参数条件查询"),
    ACCOUNT_ERROR("1000", "账户不存在或被禁用"),
    API_NOT_EXISTS("1001", "请求的接口/模块不存在"),
    API_NOT_PERMITED("1002", "没有该接口/模块的访问权限"),
    API_ARGS_ERROR("1003", "args参数json格式错误"),
    API_ARGS_EMPTY("1004", "参数%s不允许为空"),
    SIGN_ERROR("1005", "数据签名错误"),
    OVER_OCCURS("1007", "超出请求并发限制"),
    PRECISE_KEY("1008", "请传入更精确关键字查询"),
    AMOUNT_NOT_QUERY("1010", "余额不足"),
    DAY_SERACH_COUNT("1012", "超出日请求限制"),
    UNKNOWN_IP("1099", "非法IP请求"),
    SYSTEM_ERROR("9999", "系统异常");

    private String code;
    private String msg;

    private BizErrEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
