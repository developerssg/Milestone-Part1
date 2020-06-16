package com.cts.milestone.order.model;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

@ApiModel("Class representing a order tracked by the application.")
public class Order {

    private  String orderId;
    private String productId;
    private String productName;
    private String orderName;
    private BigDecimal orderPrice;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
