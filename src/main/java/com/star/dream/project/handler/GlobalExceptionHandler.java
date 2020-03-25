package com.star.dream.project.handler;

import com.star.dream.project.vo.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常拦截接口
 */
public interface GlobalExceptionHandler<T extends Throwable> {

    @ExceptionHandler/*({MyException.class,NullPointerException.class})*/   //可以直接写@ExceptionHandler,不指明异常类，会自动映射
    BaseResponse exceptionHandler(T t);   //还可以声明接收其他任意参数
}
