package cn.jamkong.controller;

import cc.innovator.common.IdGen;
import cc.innovator.common.Page;
import cc.innovator.common.QuartzManage;
import cc.innovator.common.Result;
import cc.innovator.model.QuartzJob;
import cc.innovator.service.QuartzJobService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = {"job"})
public class QuartzJobController {

    private Logger logger = LoggerFactory.getLogger(QuartzJobController.class);

    @Autowired
    private QuartzManage quartzManage;

    @Autowired
    private QuartzJobService quartzJobService;

    @ModelAttribute
    public QuartzJob get(String id) {
        if (StringUtils.isNotEmpty(id)) {
            return quartzJobService.get(id);
        }
        return new QuartzJob();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<QuartzJob> view(@PathVariable String id) {
        Result<QuartzJob> result = new Result<>();
        QuartzJob job = quartzJobService.get(new QuartzJob(id));
        result.successResult(job);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String form(QuartzJob job, Model model) {
        Result<QuartzJob> result = save(job, model);
        if (Result.ERROR_CODE.equals(result.getCode())) {
            model.addAttribute("err", result.getMsg());
        }
        return "redirect:/job/";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result<QuartzJob> save(QuartzJob job, Model model) {
        Result<QuartzJob> result = new Result<>();
        if (job == null) {
            result.errorResult("传入参数有误");
            logger.error(result.getMsg());
            return result;
        }
        boolean isNewRecord = StringUtils.isEmpty(job.getId()) ? true : false;

        try {
            if (isNewRecord) {
                job.setTriggerName(IdGen.uuid());
                if (QuartzJob.STATUS_RUNNING.equals(job.getStatus())) {
                    quartzManage.addJob(job);
                }
            } else {
                QuartzJob oldJob = quartzJobService.get(job);
                if (oldJob != null) {
                    quartzManage.deleteJob(oldJob);
                    if (QuartzJob.DEL_FLAG_NORMAL.equals(job.getDelFlag()) && QuartzJob.STATUS_RUNNING.equals(job.getStatus())) {
                        quartzManage.addJob(job);
                    }
                }
            }
            quartzJobService.save(job);
            result.successResult(job);

        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result.errorResult(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result.errorResult(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result.errorResult(e.getMessage());
        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result.errorResult(e.getMessage());
        }
        return result;
    }


    @RequestMapping(value = {"", "/"})
    public String list(QuartzJob job, Model model, HttpServletRequest request, HttpServletResponse response) {
        Page<QuartzJob> page = quartzJobService.findPage(new Page<QuartzJob>(request,response), job);
        model.addAttribute("list", page.getList());
        model.addAttribute("pageNo", page.getPageNo());
        model.addAttribute("pageSize", page.getPageSize());
        model.addAttribute("total",page.getTotal());
        return "jobList";
    }


}
