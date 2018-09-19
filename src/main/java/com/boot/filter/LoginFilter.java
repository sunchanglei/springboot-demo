package com.boot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Order(1) // 控制执行顺序；order的值越小越先执行
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/demo/*",filterName = "loginFilter")
public class LoginFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("----过滤器初始化----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对request和response进行一些预处理
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");

        logger.info("FilterDemo01执行前！！！");
        filterChain.doFilter(servletRequest, servletResponse);  //让目标资源执行，放行
        logger.info("FilterDemo01执行后！！！");
    }

    @Override
    public void destroy() {
        logger.info("----过滤器销毁----");
    }
}
