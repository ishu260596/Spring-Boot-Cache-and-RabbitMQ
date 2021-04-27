package com.example.ecommerce.model.listners;

import com.example.ecommerce.config.RBConfig;
import com.example.ecommerce.model.entities.Customers;
import com.example.ecommerce.repository.CustomerDAO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerListener {
    @Autowired
    private CustomerDAO customerDAO;

    @RabbitListener(queues = RBConfig.CUSTOMER_QUEUE)
    public void listener(Customers customers) {
        customerDAO.save(customers);
    }
}
