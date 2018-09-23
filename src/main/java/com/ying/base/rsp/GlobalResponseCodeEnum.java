package com.ying.base.rsp;

/**
 * Created by Chloe on 2018/9/22.
 */
public enum GlobalResponseCodeEnum implements ResponseCodeInterface {
    SUCCESS(200, "success"),
    BAD_REQUEST(400,"Bad Request"),
    NO_AUTHROIZED(401,"Unauthroized"),
    API_NOT_FOUND(404, "api not found"),
    PRECONDITION_FAILED(412, "Precondition Failed, e.g resonse is not valid json "),
    NO_RESPONSE(444,"No response"),
    SERVER_ERR(500, "server error"),
    TIME_OUT(504,"time out,did not receive a timely response from an upstream server"),
    CONNECTION_EXCEPTION(1000,"connection exception");

    private int code;

    private String message;

    GlobalResponseCodeEnum(int code, String message) {
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
