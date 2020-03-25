package com.star.dream.project.controller;

import com.star.dream.project.constant.Constant;
import com.star.dream.project.exception.MyException;
import com.star.dream.project.utils.ResponseUtils;
import com.star.dream.project.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller demo
 */
@RestController
@Slf4j
public class DemoController {

    @GetMapping("demo")
    public BaseResponse demo(String str){
        log.info("str:{}",str);
        if (StringUtils.isEmpty(str)){
            throw new NullPointerException();
        }
        if ("123".equals(str)){
            throw new MyException(Constant.ResultCode.MY_EXCEPTION_CODE,Constant.ResultMsg.MY_EXCEPTION_MESSAGE);
        }
        return ResponseUtils.buildSuccess();
    }
}
