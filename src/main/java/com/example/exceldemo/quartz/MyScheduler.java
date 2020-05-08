package com.example.exceldemo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class MyScheduler {

    public static void startJob() {
        //创建任务调度器scheduler
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
            //创建JobDetail实例并与TestJob绑定
            JobDetail detail = JobBuilder.newJob(TestJob.class).withIdentity("job1", "group1").build();
            //构建Tigger实例，并没5秒钟执行一次
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                    .startNow()//立即生效
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(5)//每隔5s执行一次
                            .repeatForever()).build();//一直执行


            //4、执行
            scheduler.scheduleJob(detail, trigger);
            System.out.println("--------scheduler start ! ------------");
            scheduler.start();

            //睡眠
            //TimeUnit.MINUTES.sleep(1);
            //scheduler.shutdown();
            //System.out.println("--------scheduler shutdown ! ------------");
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startJob();
    }











}
