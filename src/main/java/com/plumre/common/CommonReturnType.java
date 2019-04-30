package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/25.
 */

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 14:24
 */
public class CommonReturnType {

    private String status;
    private Object data;

    public static CommonReturnType create(Object data) {
        return create("success", data);
    }

    public static CommonReturnType create(String status, Object data) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(data);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}