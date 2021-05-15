package com.li;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private boolean status;

    private String code;

    private String message;

    Map<String,Object> data=new HashMap<String,Object>();

    public static Result success(){
        Result result=new Result();
        result.status=true;
        return result;
    }

    public static Result error(){
        Result result=new Result();
        result.status=false;
        return result;
    }

    public  Result code(String code){
        this.setCode(code);
        return this;
    }

    public  Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result CodeAndeMessage(String code,String message){
        this.setCode(code);
        this.setMessage(message);
        return this;
    }

    public Result CodeAndeMessage(ResultInfo resultInfo){
        this.setCode(resultInfo.getCode());
        this.setMessage(resultInfo.getMessage());
        return this;
    }

    public  Result data(Map<String,Object> data){
        this.setData(data);
        return this;
    }
    public  Result data(String key,Object value){
        this.data.put(key,value);
        return this;
    }


}
