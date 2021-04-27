package com.example.ecommerce.controller;

import com.example.ecommerce.config.RBConfig;
import com.example.ecommerce.model.entities.Customers;
import com.example.ecommerce.model.entities.Orders;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/saveCustomer")
    public String saveCustomer(@RequestBody Customers customers) {
        rabbitTemplate.convertAndSend(RBConfig.EXCHANGE, RBConfig.ROUTING_KEY_CUSTOMER, customers);
        return "Customer saved successfully";
    }

    @PostMapping("/placeOrder")
    public String saveCustomer(@RequestBody Orders order) {
        Customers customers = customerService.getCustomerByIdCustom(order.getCustomer_id());
        System.out.println(customers.getEmail());
        rabbitTemplate.convertAndSend(RBConfig.EXCHANGE, RBConfig.ROUTING_KEY_EMAIL, customers);
        rabbitTemplate.convertAndSend(RBConfig.EXCHANGE, RBConfig.ROUTING_KEY_ORDER, order);
        return "Order placed successfully";
    }

    @GetMapping("/customer/{id}")
    public Optional<Customers> getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/order/{id}")
    public Optional<Orders> getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/updateCustomer")
    public Customers updateCustomer(@RequestBody Customers customers) {
        return customerService.updateCustomer(customers);
    }

    @PutMapping("/updateOrder")
    public Orders updateOrder(@RequestBody Orders orders) {
        return orderService.updateOrder(orders);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomerById(id);
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable int id) {
        return orderService.deleteOrderById(id);
    }
}
