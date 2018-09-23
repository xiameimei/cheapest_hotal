package com.ying.hotels;

import com.ying.base.rsp.ResponseCodeInterface;

/**
 * Created by Chloe on 2018/9/22.
 */
public class CheapestErrorInfo implements ResponseCodeInterface {

    private int code;
    private String message;

    public CheapestErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
