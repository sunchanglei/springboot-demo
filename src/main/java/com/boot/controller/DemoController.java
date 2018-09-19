package com.boot.controller;


import com.boot.service.IDemoService;
import com.boot.vo.ApiVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunfan on 2018/7/12.
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDemoService demoService;

    @RequestMapping(value = "/testJdbc",method = RequestMethod.GET)
    public int testJdbc() {
        try {
            logger.info("测试JDBC开始了.....");
            return demoService.testJdbc();
        } catch (Exception e){
            logger.error("",e);
        }

        return 0;
    }

    @RequestMapping("/testMybatis")
    public List<ApiVo> testMybatis() {
        try {
            logger.info("测试Mybatis开始了.....");
            return demoService.testMybatis();
        } catch (Exception e){
            logger.error("",e);
            return new ArrayList<>();
        }
    }

    @RequestMapping("/count")
    @ResponseBody
    public String count(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {  //把sessionId记录在浏览器
            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(httpServletRequest.getSession().getId(), "utf-8"));
            c.setPath("/");
            //先设置cookie有效期为2天，不用担心，session不会保存2天
            c.setMaxAge(48 * 60 * 60);
            httpServletResponse.addCookie(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = httpServletRequest.getSession();
        Object count = session.getServletContext().getAttribute("count");
        return "count : " + count;
    }
}
