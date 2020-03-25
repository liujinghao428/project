package com.star.dream.project.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
@Getter
@Setter
public class MyException extends RuntimeException {
    private String code ;
    private String msg ;

    public MyException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
