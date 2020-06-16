package com.cts.milestone.product.service;

import com.cts.milestone.product.model.Product;
import com.cts.milestone.product.entity.ProductEntity;
import com.cts.milestone.product.model.ProductUpdate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class ProductTranslator {
    public ProductEntity modelToEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(getId());
        productEntity.setProductName(product.getProductName());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }

    public Product entityToModel(ProductEntity productEntity) {
        Product product = new Product();
        product.setProductId(productEntity.getProductId());
        product.setProductName(productEntity.getProductName());
        product.setPrice(productEntity.getPrice());
        return product;
    }

    public ProductEntity mergeEntity(ProductEntity productEntity, ProductUpdate productUpdate) {
        Objects.requireNonNull(productUpdate,"Product Should be not null");
        if(!isNull(productUpdate.getProductName())){
            productEntity.setProductName(productUpdate.getProductName());
        }if(!isNull(productUpdate.getPrice())){
            productEntity.setPrice(productUpdate.getPrice());
        }
        return productEntity;
    }

    public  String getId(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
