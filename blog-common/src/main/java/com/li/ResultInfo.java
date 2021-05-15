package com.li;



public enum ResultInfo {
    Not_Found("404","没找到"),
    SUCCESS("200","成功"),
    GLOBAL_ERROR("101","异常")
    ;

    private String code;
    private String message;

    ResultInfo(String code,String message){
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
