package com.example.demo.controller;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.OrderItem;
import com.example.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer-orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public CustomerOrder createOrder(@RequestParam String customerEmail, @RequestParam String customerAddress, @RequestBody List<OrderItem> items) {
        return customerOrderService.createOrder(customerEmail, customerAddress, new Date(), items);
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        customerOrderService.addOrderItem(orderId, orderItem);
    }

    @DeleteMapping("/{orderId}/items")
    public void removeOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        customerOrderService.removeOrderItem(orderId, orderItem);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal calculateOrderTotal(@PathVariable Long orderId) {
        return customerOrderService.calculateOrderTotal(orderId);
    }

    @PostMapping("/{orderId}/send")
    public void sendOrderForDelivery(@PathVariable Long orderId) {
        customerOrderService.sendOrderForDelivery(orderId);
    }

    @PutMapping("/{orderId}/status")
    public void updateOrderDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        customerOrderService.updateOrderDeliveryStatus(orderId, status);
    }
}
