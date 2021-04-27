package com.example.ecommerce.service;

import com.example.ecommerce.model.entities.Orders;
import com.example.ecommerce.repository.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDAO orderDAO;

    @Override
    @CachePut(cacheNames = "Ord", key = "#orders.id")
    public Orders updateOrder(Orders orders) {
        return orderDAO.save(orders);
    }

    @Override
    @Cacheable(cacheNames = "Ord", key = "#id")
    public Optional<Orders> getOrderById(int id) {
        System.out.println("Fetched from order table");
        return orderDAO.findById(id);
    }

    @Override
    @CacheEvict(cacheNames = "Ord", key = "#id")
    public String deleteOrderById(int id) {
        orderDAO.deleteById(id);
        return "Order canceled";
    }
}
