package com.cts.milestone.order.resource;


import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.model.OrderUpdate;
import com.cts.milestone.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    @Autowired
    private OrderService orderService;


    @ApiOperation("Creates a Order from the system. ")
    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
        String orderId = orderService.createOrder(order);
        LOG.info("Order created successfully, orderId={}",orderId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderId);
    }

    @ApiOperation("Fetches a Order from the system. ")
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId){
        Order order = orderService.getOrder(orderId);
        LOG.info("Order fetched successfully, orderId={}",orderId);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(order);
    }
    @ApiOperation("Updates a Order from the system. 404 if the Order's identifier is not found.")
    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable String orderId, @RequestBody OrderUpdate orderUpdate){
        String eTag = orderService.updateOrder(orderId, orderUpdate);
        LOG.info("Order Updated successfully, orderId={}",orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .eTag(eTag)
                .build();
    }
    @ApiOperation("Deletes a Order from the system. 404 if the Order's identifier is not found.")
    @DeleteMapping("/{orderId}")
    public  ResponseEntity<Void> deleteOrder(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
        LOG.info("Employee deleted successfully, orderId={}",orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
