package com.ying.base.rsp;

/**
 * Created by Chloe on 2018/9/22.
 */
public class GlobalErrorInfoException extends Exception {

    private ResponseCodeInterface errorInfo;

    public GlobalErrorInfoException (ResponseCodeInterface errorInfo) {
        this.errorInfo = errorInfo;

    }

    public ResponseCodeInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ResponseCodeInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}
