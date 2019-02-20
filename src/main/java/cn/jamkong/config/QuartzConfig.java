package cn.jamkong.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz 配置类
 */
@Configuration
public class QuartzConfig {

    @Bean(name = "quartzJobFactory")
    public AdaptableJobFactory jobFactory() {
        return new AdaptableJobFactory();
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler(AdaptableJobFactory quartzJobFactory) throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.afterPropertiesSet();
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

}
