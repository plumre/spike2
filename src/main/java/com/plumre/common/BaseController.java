package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/25.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 15:11
 */
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonReturnType handleException(Exception e) {
        HashMap<Object, Object> map = new HashMap<>();
        if (e instanceof BusinessException) {
            map.put("errorCode", ((BusinessException) e).getErrorCode());
            map.put("errorMessage", ((BusinessException) e).getErrorMessage());
        } else {
            System.out.println(e.getMessage());
            System.out.println(e);
            map.put("errorCode", BusinessErrorEnum.UNKNOWN_ERROR.getErrorCode());
            map.put("errorMessage", BusinessErrorEnum.UNKNOWN_ERROR.getErrorMessage());
        }
        return CommonReturnType.create("fail", map);
    }
}