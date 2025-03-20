package com.zut.tlias.exception;

import com.zut.tlias.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// 全局异常处理，用来捕获项目抛出的异常
public class GlobalExceptionHandler {
    //捕获所有异常
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.err("错误，请联系管理员");
    }
}
