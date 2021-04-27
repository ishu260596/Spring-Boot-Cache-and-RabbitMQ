package com.example.ecommerce.service;

import com.example.ecommerce.model.entities.Customers;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerService {
    Optional<Customers> getCustomerById(int id);

    Customers updateCustomer(Customers customers);

    String deleteCustomerById(int id);

    String findCustomerEmail(int customer_id);
    Customers getCustomerByIdCustom(int customer_id);
}
