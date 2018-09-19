package com.boot.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

//@Configuration
//@EnableScheduling
public class DemoTask {

    /**
     * 我们希望这个方法每10秒打印一次.
     * cron: 定时任务表达式.
     *
     * 指定：秒，分钟，小时，日期，月份，星期，年（可选）.
     * *：任意.
     *
     */

    @Scheduled(cron="0/10 * * * * *")
    public void tast1(){
        System.out.println("MyTask.tast1(),"+new Date());
    }

    /**
     * 我们希望这个方法每1分钟打印一次.
     */
    @Scheduled(cron="0 0/1 * * * *")
    public void tast2(){
        System.out.println("MyTask.tast2(),"+new Date());
    }
}
