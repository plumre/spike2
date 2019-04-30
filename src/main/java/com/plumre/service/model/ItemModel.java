package com.plumre.service.model;

/*
 * Created by renhongjiang on 2019/3/26.
 */

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/26 10:32
 */
public class ItemModel implements Serializable {
    private static final long serialVersionUID = -4805489892519895199L;

    private Integer id;
    @NotBlank(message = "商品名称需要填写")
    private String title;

    @NotNull(message = "商品价格还没填写呢")
    @Min(value = 0, message = "这个价格不符合地球人的习惯")
    private BigDecimal price;
    @NotNull(message = "库存需要填写")
    @Min(value = 0, message = "这个库存不符合地球人的习惯")
    private Integer stock;

    @NotBlank(message = "商品描述丢了吗")
    private String description;

    private Integer sales;

    @NotBlank(message = "图片能更好的展现产品呢")
    private String imgUrl;

    /**
     * 聚合模型
     */
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}