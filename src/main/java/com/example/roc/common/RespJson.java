package com.example.roc.common;

import lombok.Data;

@Data
public class RespJson {
    private int code;
    private String msg;
    private Object data;

    public RespJson(RespEnum respEnum) {
        this.code = respEnum.getCode();
        this.msg = respEnum.getMsg();
        this.data = respEnum.getData();
    }

    public RespJson(RespEnum respEnum, String msg) {
        this.code = respEnum.getCode();
        this.msg = null == msg ? respEnum.getMsg() : msg;
        this.data = respEnum.getData();
    }

    public RespJson(RespEnum respEnum, Object data) {
        this.code = respEnum.getCode();
        this.msg = respEnum.getMsg();
        this.data = null == data ? respEnum.getData() : data;
    }

    public static RespJson success() {
        return new RespJson(RespEnum.SUCCESS);
    }

    public static RespJson success(Object data) {
        return new RespJson(RespEnum.SUCCESS, data);
    }

    public static RespJson operationResult(int row,Object data) {
        if (0 == row) {
            return RespJson.error();
        }
        return RespJson.success(data);
    }

    public static RespJson error() {
        return new RespJson(RespEnum.ERROR);
    }

    public static RespJson error(RespEnum respEnum) {
        return new RespJson(respEnum);
    }

    public static RespJson error(RespEnum respEnum, String msg) {
        return new RespJson(respEnum, msg);
    }

    public static RespJson error(String msg) {
        return new RespJson(RespEnum.ERROR, msg);
    }

    public static RespJson row(int row) {
        return 1 == row ? RespJson.success() : RespJson.error();
    }

}
