package com.boot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Calendar;

/**
 * 登录拦截器。
 */
public class LoginTimeInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Controller处理之前进行调用，返回false则请求终止
     * 执行顺序 第一
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        logger.info("执行LoginTime.preHandle方法-->01");
        Calendar cal = Calendar.getInstance();
        //获得当前时间对应的小时数,例如：12:05-->12,13:15-->13
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (8 <= hour && hour < 22) {
            return true;  //通过拦截器，继续执行请求
        } else {//给定的时间之外禁止登录
            request.setAttribute("msg", "非登录时段");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return false;  //没有通过拦截器，返回登录页面
        }
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
        logger.info("执行LoginTime.afterCompletion方法-->03");
        super.afterCompletion(request, response, handler, ex);
    }
}
