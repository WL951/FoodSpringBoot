package com.wl.foodspringboot.Domain;

/**
 * 自定义异常类
 */
public class FoodException extends RuntimeException {
    private String code;
    private String msg;

    public FoodException() {
    }

    public FoodException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
