package com.li;



public enum ResultInfo {
    LOGIN_SUCCESS("123","登录成功"),
    SUCCESS("200","成功"),
    LOGIN_FAIL("124","登录失败"),
    Not_Found("404","没找到"),
    GLOBAL_ERROR("101","异常"),
    QUERYVO_NULL("405","传递的QUERRYVO为null")
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
