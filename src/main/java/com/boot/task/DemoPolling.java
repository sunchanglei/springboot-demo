package com.boot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 启动服务开启轮询
 * 优点：定时Task如果执行过慢且频率较快，容易积压大量线程对服务器造成压力。轮询则不存在该问题。
 * 注意点：1、设定开关；2、服务启动时存在数据库尚未连接成功的情况；3、设置休眠时间；4、设置独立线程；
 */
public class DemoPolling {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(DemoPolling.class);
    public static final boolean IS_OPEN = false;

//    @PostConstruct
    private void polling(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    if(!IS_OPEN){
                        logger.info("停止轮询！");
                        break;
                    }

                    logger.info("开始轮询！");

                    try {
                        Thread.sleep(10000);
                        logger.info("休息10s,再进行轮询");
                    } catch(Exception e){
                        logger.error("",e);
                    }
                }
            }
        }).start();
    }
}
