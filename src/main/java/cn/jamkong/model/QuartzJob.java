package cn.jamkong.model;

import cc.innovator.common.DataEntity;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

public class QuartzJob extends DataEntity<QuartzJob> {

    // 状态：运行
    public static final Integer STATUS_RUNNING = 1;
    // 状态：停止
    public static final Integer STATUS_NOT_RUNNING = 0;

    /**
     * 任务名
     */
    @NotBlank
    private String name;


    /**
     * cron 表达式
     */
    @NotBlank
    private String cronExp;

    /**
     * 任务执行时调用哪个类的方法 包名+类名，完全限定名
     */
    @NotBlank
    private String beanName;

    /**
     * 任务调用的方法名
     */
    private String methodName;

    /**
     * 触发器名称
     */
    @NotBlank
    private String triggerName;

    /**
     * 触发器分组
     */
    private String groupName;
    /**
     * 任务状态
     */
    @NotBlank
    private Integer status = STATUS_NOT_RUNNING;

    /**
     * 是否立即启动
     */
    private boolean runNow;

    public QuartzJob() {
    }

    public QuartzJob(String id) {
        super(id);
    }

    public String getCronExp() {
        return cronExp;
    }

    public void setCronExp(String cronExp) {
        this.cronExp = cronExp;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        if (StringUtils.isEmpty(groupName)) {
            return "DEFAULT";
        }
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isRunNow() {
        return runNow;
    }

    public void setRunNow(boolean runNow) {
        this.runNow = runNow;
    }
}
