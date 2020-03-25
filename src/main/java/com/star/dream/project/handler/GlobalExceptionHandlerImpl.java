package com.star.dream.project.handler;

import com.star.dream.project.exception.MyException;
import com.star.dream.project.utils.ResponseUtils;
import com.star.dream.project.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常拦截实现，可以定义不同异常不同处理逻辑
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandlerImpl implements GlobalExceptionHandler {

    @ExceptionHandler
    public BaseResponse myExceptionHandler(MyException e){
        log.error("捕获到MyException：{}",e.getMsg(),e);
        return ResponseUtils.buildFail(e.getCode(),e.getMsg());
    }

    @Override
    public BaseResponse exceptionHandler(Throwable t) {
        log.error("捕获到异常：{}",t.getMessage(),t);
        return ResponseUtils.buildFail();
    }
}
