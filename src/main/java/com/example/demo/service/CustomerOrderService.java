package com.example.demo.service;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, OrderItem item);
    BigDecimal calculateOrderTotal(Long orderId);
    void sendOrderForDelivery(Long orderId);
    void updateOrderDeliveryStatus(Long orderId, String status);
}
