package com.example.demo.service;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.OrderItem;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        CustomerOrder customerOrder = new CustomerOrder(null, customerEmail, customerAddress, orderDate, items);
        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        Optional<CustomerOrder> orderOpt = customerOrderRepository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.addOrderItem(item);
            customerOrderRepository.save(order);
        });
    }

    @Override
    public void removeOrderItem(Long orderId, OrderItem item) {
        Optional<CustomerOrder> orderOpt = customerOrderRepository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.removeOrderItem(item);
            customerOrderRepository.save(order);
        });
    }

    @Override
    public BigDecimal calculateOrderTotal(Long orderId) {
        Optional<CustomerOrder> orderOpt = customerOrderRepository.findById(orderId);
        return orderOpt.map(CustomerOrder::calculateTotal).orElse(BigDecimal.ZERO);
    }

    @Override
    public void sendOrderForDelivery(Long orderId) {
        Optional<CustomerOrder> orderOpt = customerOrderRepository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.sendForDelivery();
            customerOrderRepository.save(order);
        });
    }

    @Override
    public void updateOrderDeliveryStatus(Long orderId, String status) {
        Optional<CustomerOrder> orderOpt = customerOrderRepository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.updateDeliveryStatus(status);
            customerOrderRepository.save(order);
        });
    }
}
