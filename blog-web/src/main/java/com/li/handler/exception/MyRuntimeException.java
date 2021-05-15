package com.li.handler.exception;

import com.li.ResultInfo;

public class MyRuntimeException extends RuntimeException{

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyRuntimeException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public MyRuntimeException(ResultInfo info) {
        this.code = info.getCode();
        this.message = info.getMessage();
    }
}
