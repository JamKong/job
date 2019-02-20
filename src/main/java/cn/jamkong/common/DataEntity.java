package cn.jamkong.common;

import java.util.Date;

public abstract class DataEntity<T> extends BaseEntity<T> {

    private Date createDate;

    private Date updateDate;

    private Page<T> page;

    private String remarks;

    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }


    public DataEntity(String id) {
        super(id);
    }

    @Override
    protected void preInsert() {
        this.createDate = new Date();
        this.updateDate = this.createDate;
        setId(IdGen.uuid());
    }

    @Override
    protected void preUpdate() {
        this.updateDate = new Date();
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
