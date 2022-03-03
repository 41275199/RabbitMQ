package com.example.roc.common;

public enum RespEnum {
    SUCCESS(200, "成功", null),
    ERROR(500, "服务器繁忙", null);
    private int code;
    private String msg;
    private Object data;

    RespEnum(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
