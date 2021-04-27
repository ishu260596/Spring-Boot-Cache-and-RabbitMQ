package com.example.ecommerce.model.listners;

import com.example.ecommerce.config.RBConfig;
import com.example.ecommerce.model.entities.Customers;
import com.example.ecommerce.model.entities.Orders;
import com.example.ecommerce.repository.OrderDAO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    private OrderDAO orderDAO;

    @RabbitListener(queues = RBConfig.ORDER_QUEUE)
    public void listener(Orders orders) {
        orderDAO.save(orders);
    }
}
