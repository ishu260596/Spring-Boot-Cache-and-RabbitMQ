package com.example.ecommerce.service;

import com.example.ecommerce.model.entities.Customers;
import com.example.ecommerce.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Cacheable(cacheNames = "Cus", key = "#id")
    public Optional<Customers> getCustomerById(int id) {
        System.out.println("Fetched from customer table");
        return customerDAO.findById(id);
    }

    @Override
    @CachePut(cacheNames = "Cus", key = "#customers.id")
    public Customers updateCustomer(Customers customers) {
        return customerDAO.save(customers);
    }

    @Override
    @CacheEvict(cacheNames = "Cus", key = "#id")
    public String deleteCustomerById(int id) {
        customerDAO.deleteById(id);
        return "Customer removed";
    }

    @Override
    public String findCustomerEmail(int customer_id) {
        return customerDAO.findCustomerEMail(customer_id);
    }

    @Override
    public Customers getCustomerByIdCustom(int customer_id) {
        return customerDAO.findCustomerById(customer_id);
    }


}
