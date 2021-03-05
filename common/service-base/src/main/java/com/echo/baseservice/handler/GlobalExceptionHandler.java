package com.echo.baseservice.handler;

import com.echo.baseservice.exception.GuliException;
import com.echo.commonutils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/03 20:15
 * @Description: com.echo.baseservice.handler
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    @ResponseBody
    @ExceptionHandler(GuliException.class)
    public Result error(GuliException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMessage());
    }
}
