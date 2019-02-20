package cn.jamkong.common;

import java.io.Serializable;

/**
 * @author jamkong
 */
public abstract class BaseEntity<T> implements Serializable {


    private static final long serialVersionUID = -6037326326230994873L;

    /**
     * 实体编号（唯一标识）
     */
    protected String id;
    protected String delFlag;

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    protected abstract void preInsert();

    protected abstract void preUpdate();


    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

}
