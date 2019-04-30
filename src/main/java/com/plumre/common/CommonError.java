package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/25.
 */

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 14:26
 */
public interface CommonError {

    int getErrorCode();

    String getErrorMessage();

    CommonError setErrorMessage(String errorMessage);

}