package com.example.annotation.common.annotation;

import com.example.annotation.common.init.SnowflakeInit;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private final Gson gson;
    private final SnowflakeInit snowflakeInit;

    private ThreadLocal<String> idLocal = new ThreadLocal<>();

    public LogAspect(Gson gson, SnowflakeInit snowflakeInit) {
        this.gson = gson;
        this.snowflakeInit = snowflakeInit;
    }

    @Pointcut("@annotation(com.example.annotation.common.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String methodName = " " + signature.getName();
        Log syslog = method.getAnnotation(Log.class);
        beforeLog(syslog, methodName, point);
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        afterLog(syslog, methodName, result, time);
        return result;
    }
    private void beforeLog(Log syslog, String methodName, ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            Object requestBean = args[i];
            sb.append(this.gson.toJson(requestBean) + ",");
        }
        String requestId = snowflakeInit.getId();
        idLocal.set(requestId);
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        logger.info("requestId: [{}] method [{}] params [{}]", requestId, syslog.className()+ methodName, sb.toString());
    }

    private void afterLog(Log syslog, String methodName, Object result, long time) {
        String requestId = idLocal.get();
        idLocal.remove();
        logger.info("requestId: [{}] method [{}] params [{}] times [{}]", requestId,syslog.className() + methodName, this.gson.toJson(result), time);
    }
}
