package com.example.exceldemo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobDetail:"+context.getJobDetail().getJobDataMap().get("job1"));
        System.out.println("Trigger:"+context.getTrigger().getJobDataMap().get("trigger1"));
        String time= new SimpleDateFormat("YYYY-MM-dd HH:MM:ss").format(new Date());
        System.out.println("TestJob类启动，当前时间:" + time);
    }
}
