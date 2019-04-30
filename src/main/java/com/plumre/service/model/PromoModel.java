package com.plumre.service.model;

/*
 * Created by renhongjiang on 2019/3/27.
 */

import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/27 9:57
 */
public class PromoModel implements Serializable {

    private static final long serialVersionUID = 8907508147451956156L;

    private Integer id;
    private Integer status;

    private String promoName;

    private DateTime startTime;

    private Integer itemId;

    private BigDecimal promoItemPrice;
    private DateTime endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}