package com.example.ecommerce.service;

import com.example.ecommerce.model.entities.Orders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface OrderService {
    Orders updateOrder(Orders orders);

    Optional<Orders> getOrderById(int id);

    String deleteOrderById(int id);
}
