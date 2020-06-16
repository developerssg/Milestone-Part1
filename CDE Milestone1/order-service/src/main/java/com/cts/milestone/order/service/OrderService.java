package com.cts.milestone.order.service;

import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.model.Product;
import com.cts.milestone.order.repository.OrderRepository;
import com.cts.milestone.order.entity.OrderEntity;
import com.cts.milestone.order.model.OrderUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderTranslator orderTranslator;
    @Autowired
    private RestTemplate restTemplate;

    public String createOrder(Order order) {
        OrderEntity orderEntity = orderTranslator.modelToEntity(order);
        orderRepository.save(orderEntity);
        return orderEntity.getOrderId();
    }

    public Order getOrder(String orderId) {
        Optional<OrderEntity> orderEntity= orderRepository.findById(orderId);
        if(!orderEntity.isPresent()){
            throw  new RuntimeException("Order is not found ,orderId="+orderId);
        }
        Product product = restTemplate.getForObject("http://localhost:8080/products/" +orderEntity.get()
                                      .getProductId(), Product.class);
        if(isNull(product)){
            throw new RuntimeException("Product is not found ,productId="+ orderEntity.get().getProductId());
        }
        return orderTranslator.entityToModel(orderEntity.get(),product);
    }

    public String updateOrder(String orderId, OrderUpdate orderUpdate) {
        Optional<OrderEntity> orderEntity= orderRepository.findById(orderId);
        if(!orderEntity.isPresent()){
            throw  new RuntimeException("Order is not found, orderId="+orderId);
        }
        OrderEntity mergerOrder = orderTranslator.mergeEntity(orderEntity.get(), orderUpdate);
        orderRepository.save(mergerOrder);
        return orderId;
    }
    public void deleteOrder(String orderId) {
        Optional<OrderEntity> orderEntity= orderRepository.findById(orderId);
        if(!orderEntity.isPresent()){
            throw  new RuntimeException("Order is not found, orderId="+orderId);
        }
        orderRepository.deleteById(orderId);
    }


}
