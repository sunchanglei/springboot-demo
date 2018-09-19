package com.boot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * 防双击拦截器。
 */
public class ToenInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Controller处理之前进行调用，返回false则请求终止
     * 执行顺序 第一
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        return TokenProccessor.isRepeatSubmit(request);//判断用户是否是重复提交
    }
    /**
     * 在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作
     * 执行顺序 第七
     */
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("执行LoginTime.postHandle方法-->02");
        super.postHandle(request, response, handler, modelAndView);
    }
    /**
     * 在DispatcherServlet渲染了视图后执行，用于清除资源
     * 执行顺序 第八
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        String token = TokenProccessor.getInstance().makeToken();//创建令牌
        logger.info("在FormServlet中生成的token："+token);
        request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
        response.setHeader("token",token);
    }
}
