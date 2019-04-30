package com.plumre.validator;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 9:23
 */
public class ValidationResult {

    private boolean hasErrors = false;

    private Map<String,String> errorMessageMap = new HashMap<>();

    public String getErrorMessage() {
        return StringUtils.join(errorMessageMap.values().toArray(), ",");
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMessageMap() {
        return errorMessageMap;
    }

    public void setErrorMessageMap(Map<String, String> errorMessageMap) {
        this.errorMessageMap = errorMessageMap;
    }
}