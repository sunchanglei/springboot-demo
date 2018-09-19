package com.boot.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *  使用HttpSession时，触发监听程序
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    /**
     * Default constructor.
     */
    public MyHttpSessionListener() {
        // TODO Auto-generated constructor stub
    }

    public int count=0;//记录session的数量
    //监听session的创建,synchronized 防并发bug
    @Override
    public synchronized void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("【HttpSessionListener监听器】count++  增加");
        count++;
        arg0.getSession().getServletContext().setAttribute("count", count);

    }
    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent arg0) {//监听session的撤销
        System.out.println("【HttpSessionListener监听器】count--  减少");
        count--;
        arg0.getSession().getServletContext().setAttribute("count", count);
    }
}
