package com.guli.common.handler;

import com.guli.common.vo.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //所有exception捕捉
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();  //控制台打印
        return R.error();
    }
}
