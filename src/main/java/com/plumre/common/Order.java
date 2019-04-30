package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/4.
 */

import java.io.Serializable;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/4 11:10
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 3846530226972761410L;
    private String id;
    private String name;
    /**
     * 存储消息发送的唯一标识
     */
    private String messageId;

    public Order() {
    }

    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}