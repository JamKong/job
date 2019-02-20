package cn.jamkong;

import cc.innovator.common.QuartzManage;
import cc.innovator.model.QuartzJob;
import cc.innovator.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SpringBoot 项目重启后，将会执行该run方法将之前的Job恢复
 */
@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private QuartzJobService jobService;
    @Autowired
    private QuartzManage quartzManage;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        QuartzJob job = new QuartzJob();
        job.setStatus(QuartzJob.STATUS_RUNNING);
        List<QuartzJob> runningJobs = jobService.findList(job);
        if (runningJobs != null && runningJobs.size() > 0) {
            quartzManage.initScheduleJob(runningJobs);
        }
    }
}
