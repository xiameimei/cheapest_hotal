package com.ying.base.rsp;

/**
 * Created by Chloe on 2018/9/22.
 */
public class RspData<T> {
    int code;
    String message;

    T data;

    public RspData(ResponseCodeInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public RspData(T resultData) {
        this.code = GlobalResponseCodeEnum.SUCCESS.getCode();
        this.message = GlobalResponseCodeEnum.SUCCESS.getMessage();
        this.data = resultData;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
