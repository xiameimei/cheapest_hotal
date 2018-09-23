package com.ying.base.rsp;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chloe on 2018/9/22.
 */
@RestControllerAdvice
public class GlobalErrorInfoHandler {

    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public RspData errorHandlerOverJson(HttpServletRequest request,
                                        GlobalErrorInfoException exception) {
            ResponseCodeInterface errorInfo = exception.getErrorInfo();
            RspData result = new RspData(errorInfo);
            return result;

    }


}
