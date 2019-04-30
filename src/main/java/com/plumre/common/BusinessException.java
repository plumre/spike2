package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/25.
 */

/**
 * 包装器业务异常类实现
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 14:40
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    /**
     * 直接接受Enum的传参用于构造业务异常
     */
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    /**
     * 接收自定义errorMessage来构造业务异常
     */
    public BusinessException(CommonError commonError, String errorMessage) {
        this(commonError);
        this.setErrorMessage(errorMessage);

    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return this.commonError.getErrorMessage();
    }

    @Override
    public CommonError setErrorMessage(String errorMessage) {
        this.commonError.setErrorMessage(errorMessage);
        return this;
    }
}