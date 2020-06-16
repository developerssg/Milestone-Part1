package com.cts.milestone.order.repository;

import com.cts.milestone.order.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity,String> {
}
