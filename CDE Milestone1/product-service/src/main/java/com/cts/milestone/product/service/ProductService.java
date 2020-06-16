package com.cts.milestone.product.service;

import com.cts.milestone.product.model.Product;
import com.cts.milestone.product.repository.ProductRepository;
import com.cts.milestone.product.entity.ProductEntity;
import com.cts.milestone.product.model.ProductUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTranslator productTranslator;

    public String createProduct(Product product) {
        ProductEntity productEntity = productTranslator.modelToEntity(product);
        productRepository.save(productEntity);
        return productEntity.getProductId();
    }

    public Product getProduct(String productId) {
        Optional<ProductEntity> productEntity= productRepository.findById(productId);
        if(!productEntity.isPresent()){
            throw  new RuntimeException("Product is not found, productId="+productId);
        }
        return productTranslator.entityToModel(productEntity.get());
    }

    public String updateProduct(String productId, ProductUpdate productUpdate) {
        Optional<ProductEntity> productEntity= productRepository.findById(productId);
        if(!productEntity.isPresent()){
            throw  new RuntimeException("Product is not found, productId="+productId);
        }
        ProductEntity mergerProduct = productTranslator.mergeEntity(productEntity.get(), productUpdate);
        productRepository.save(mergerProduct);
        return productId;
    }
    public void deleteProduct(String productId) {
        Optional<ProductEntity> productEntity= productRepository.findById(productId);
        if(!productEntity.isPresent()){
            throw  new RuntimeException("Product is not found, productId="+productId);
        }
        productRepository.delete(productEntity.get());
    }


}
