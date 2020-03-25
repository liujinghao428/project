package com.star.dream.project.utils;

import com.star.dream.project.constant.Constant;
import com.star.dream.project.vo.BaseResponse;
import org.apache.commons.lang.StringUtils;

/**
 * 返回值操作工具
 */
public class ResponseUtils<T> {

    public static BaseResponse buildSuccess(){
        return buildSuccess(null);
    }

    public static BaseResponse buildSuccess(Object data){
        return BaseResponse.builder()
                .code(Constant.ResultCode.SUCCESS_CODE)
                .msg(Constant.ResultMsg.SUCCESS_MESSAGE)
                .success(true)
                .data(data)
                .build();
    }

    public static BaseResponse buildFail(){
        return buildFail(Constant.ResultCode.ERROR_CODE,Constant.ResultMsg.ERROR_MESSAGE);
    }

    public static BaseResponse buildFail(String code,String msg){
        return buildResult(code, msg, null);
    }

    public static BaseResponse buildResult(String code,String msg,Object data){
        return BaseResponse.builder()
                .code(StringUtils.isEmpty(code) ? Constant.ResultCode.SUCCESS_CODE : code)
                .msg(StringUtils.isEmpty(msg) ? (Constant.ResultCode.SUCCESS_CODE.equals(code) ? Constant.ResultMsg.SUCCESS_MESSAGE : Constant.ResultMsg.ERROR_MESSAGE) : msg)
                .success(Constant.ResultCode.SUCCESS_CODE.equals(code))
                .data(data)
                .build();
    }
}
