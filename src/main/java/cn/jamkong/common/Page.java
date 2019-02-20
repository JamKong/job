package cn.jamkong.common;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    private int pageNo = 1; // 当前页码
    private int pageSize = 10; // 页面大小，设置为“-1”表示不进行分页（分页无效）
    private Long total;// 总记录数，设置为“-1”表示不查询总数

    private List<T> list = new ArrayList<T>();

    public Page(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotEmpty(request.getParameter("pageNo"))) {
            this.pageNo = Integer.parseInt(request.getParameter("pageNo"));
        } else {
            pageNo = 1;
        }
        if (StringUtils.isNotEmpty(request.getParameter("pageSize"))) {
            this.pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } else {
            pageSize = 10;
        }
    }


    public Page<T> fromPageInfo(PageInfo<T> pageInfo) {
        this.list = pageInfo.getList();
        this.pageNo = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
