package com.ying.hotels.lib.impl;

/**
 * Created by Chloe on 2018/9/21.
 */
public class SearchAirportRspErr {
    private int status;
    private String message;
    private String more_info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMore_info() {
        return more_info;
    }

    public void setMore_info(String more_info) {
        this.more_info = more_info;
    }
}
