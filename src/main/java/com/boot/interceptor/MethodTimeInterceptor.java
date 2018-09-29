package com.boot.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * 打印方法执行的时间
 */
public class MethodTimeInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(MethodTimeInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        Object result = invocation.proceed();
        clock.stop();  //计时结束
        logger.debug("==================" + invocation.getClass().getName() + "." + invocation.getMethod().getName() + " costs " + clock.getTotalTimeMillis() + " ms");
        return result;
    }

}
