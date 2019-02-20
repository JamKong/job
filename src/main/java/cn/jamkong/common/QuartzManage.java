package cn.jamkong.common;

import cc.innovator.model.QuartzJob;
import org.quartz.*;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Quartz任务管理工具类
 */
@Component
public class QuartzManage {

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    /**
     * 新增一个任务，立即执行一次
     *
     * @param job
     * @throws SchedulerException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void addJob(QuartzJob job) throws SchedulerException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //通过类名获取实体类，即要执行的定时任务的类
        Class<?> targetClass = Class.forName(job.getBeanName());
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setTargetClass(targetClass);
        factoryBean.setTargetObject(targetClass.newInstance());
        factoryBean.setTargetMethod(job.getMethodName());
        factoryBean.setName(job.getName());
        factoryBean.setGroup(job.getGroupName());
        factoryBean.setConcurrent(false);
        try {
            factoryBean.afterPropertiesSet();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        JobDetail jobDetail = factoryBean.getObject();
//        org.quartz.Job jobEntity = (org.quartz.Job) clazz.newInstance();
        //通过实体类和任务名创建 JobDetail
//        org.quartz.JobDetail jobDetail = JobBuilder.newJob(jobEntity.getClass())
//                .withIdentity(job.getName()).build();

        //通过触发器名和cron 表达式创建 Trigger
        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(job.getTriggerName(), job.getGroupName());
        if (job.isRunNow()) {
            triggerBuilder = triggerBuilder.startNow();
        }

        triggerBuilder = triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExp()));

        Trigger cronTrigger = triggerBuilder.build();
        //执行定时任务
        scheduler.scheduleJob(jobDetail, cronTrigger);
        // 启动
        if (scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 更新job cron表达式
     *
     * @param job
     * @throws SchedulerException
     */
    public void updateJobCron(QuartzJob job) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getGroupName());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExp());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 删除一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void deleteJob(QuartzJob job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getName());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void resumeJob(QuartzJob job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getName());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 立即执行job
     *
     * @param job
     * @throws SchedulerException
     */
    public void runJobNow(QuartzJob job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getName());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 暂停一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void pauseJob(QuartzJob job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getName());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 初始化调度任务
     *
     * @param jobs
     */
    public void initScheduleJob(List<QuartzJob> jobs) {
        if (jobs != null && jobs.size() > 0) {
            for (QuartzJob job : jobs) {
                try {
                    addJob(job);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
