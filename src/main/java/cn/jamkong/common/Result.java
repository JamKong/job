package cn.jamkong.common;

public class Result<T> {

    public static final String ERROR_CODE = "0";
    public static final String SUCCESS_CODE = "1";


    private String code = SUCCESS_CODE;
    private String msg;
    private Long total;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result<T> successResult(T data) {
        this.data = data;
        this.code = SUCCESS_CODE;
        return this;
    }

    public Result<T> errorResult(String msg) {
        this.msg = msg;
        this.code = ERROR_CODE;
        return this;
    }

    public Result<T> successResultWithMsg(String msg){
        this.msg = msg;
        this.code = SUCCESS_CODE;
        return this;
    }


}
