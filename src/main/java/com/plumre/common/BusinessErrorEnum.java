package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/25.
 */

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 14:28
 */
public enum BusinessErrorEnum implements CommonError {

    /**
     * PARAMETER_INVALIDATION
     */
    PARAMETER_INVALIDATION(10001, "参数不合法"),
    /**
     * UNKNOWN_ERROR
     */
    UNKNOWN_ERROR(10002, "未知错误"),
    /**
     * USER_NOT_EXISTS
     */
    USER_NOT_EXISTS(20001, "用户不存在"),
    /**
     * USER_LOGIN_FAIL
     */
    USER_LOGIN_FAIL(20002, "用户名或密码不正确"),
    /**
     * USER_NO_LOGIN
     */
    USER_NO_LOGIN(20003, "用户还未登录"),
    /**
     * STOCK_NOT_ENOUGH
     */
    STOCK_NOT_ENOUGH(30001, "库存数量不足");

    private int errorCode;
    private String errorMessage;

    BusinessErrorEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public CommonError setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}