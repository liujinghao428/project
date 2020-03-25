package com.star.dream.project.aop;

import com.star.dream.project.constant.Constant;
import com.star.dream.project.exception.MyException;
import com.star.dream.project.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * AOP
 */
@Aspect
@Slf4j
@Component
public class AopAspect {

    @Pointcut("execution(* com.star.dream.project.controller..*(..))")
    private void traceLogAspect() {
    }

    /**
     * 请求接口前生成traceId
     */
    @Before(value = "traceLogAspect()")
    public void doBefore(JoinPoint joinPoint) {
//        Object obj = joinPoint.getThis();
//        log.info(JSON.toJSONString(obj));
//        String traceId = null;
//        if(obj instanceof HttpServletRequest){
//            traceId = ((HttpServletRequest)obj).getParameter("traceId");
//        }
//        if (StringUtils.isEmpty(traceId)) {
//            traceId = UUID.randomUUID().toString().replaceAll("-", "");
//            MDC.put("traceId", traceId);
//        }
    }

    /**
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "traceLogAspect()")
    public void doAfterReturning(Object obj) {
        try {
            if (obj instanceof BaseResponse) {
                BaseResponse rsp = (BaseResponse) obj;
                rsp.setTraceId(MDC.get("traceId"));
            }
            log.info("返回结果:{}",obj.toString());
            MDC.clear();
        } catch (Exception ex) {
            log.error("动态赋值traceId异常", ex);
        }
    }

    /**
     * @param joinPoint
     * @return
     */
    @Around("traceLogAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        String interfaceName = String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            if (ex instanceof MyException) {
                MyException e = (MyException) ex;
                result = BaseResponse.builder().code(e.getCode()).msg(e.getMsg()).build();
                log.error(String.format("调用接口%s异常，错误信息:%s", interfaceName, e.getMsg()), e);
            } else {
                result = BaseResponse.builder().code(Constant.ResultCode.ERROR_CODE).msg(Constant.ResultMsg.ERROR_MESSAGE).build();
                log.error(String.format("调用接口%s异常，错误信息:%s", interfaceName, ex.toString()), ex);
            }
        }
        return result;
    }
}
