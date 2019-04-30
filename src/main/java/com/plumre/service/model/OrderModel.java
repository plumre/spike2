package com.plumre.service.model;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import java.math.BigDecimal;

/**
 * 交易模型
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 16:49
 */
public class OrderModel {

    private String id;
    private Integer userId;
    private Integer itemId;
    private Integer promoId;

    private BigDecimal itemPrice;
    private Integer quantity;
    private BigDecimal orderAmount;

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
}