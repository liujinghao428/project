package com.star.dream.project.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回基类
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {

    private String code;
    private String msg;
    private T data;
    private boolean success;
    private String traceId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append(",\"data\":")
                .append(data);
        sb.append(",\"success\":")
                .append(success);
        sb.append(",\"traceId\":\"")
                .append(traceId).append('\"');
        sb.append("}");
        return sb.toString();
    }
}
