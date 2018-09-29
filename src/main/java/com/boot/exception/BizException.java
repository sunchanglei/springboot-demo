package com.boot.exception;

public class BizException extends Exception {

    private String code;

    private String msg;

    public BizException(String msg) {
        super(msg);
    }

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
