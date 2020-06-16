package com.cts.milestone.product.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="PRODUCT")
public class ProductEntity {

    @Id
    @Column(name = "PRODUCT_ID")
    private  String productId;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_PRICE")
    private BigDecimal price;
    @Version
    @Column(name = "VERSION")
    private Integer version;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
