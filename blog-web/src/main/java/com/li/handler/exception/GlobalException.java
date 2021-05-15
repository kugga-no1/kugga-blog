package com.li.handler.exception;

import com.li.Result;
import com.li.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        return Result.error().CodeAndeMessage(ResultInfo.GLOBAL_ERROR);
    }

    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public Result MyRuntimeException(MyRuntimeException e){
        e.printStackTrace();
        return Result.error().CodeAndeMessage(e.getCode(),e.getMessage());
    }

}
