package com.example.ecommerce.repository;

import com.example.ecommerce.model.entities.Customers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;

@Repository
public interface CustomerDAO extends CrudRepository<Customers, Integer> {
    @Query("select c.email from Customers c where c.id=?1")
    String findCustomerEMail(int customer_id);

    @Query("select c from Customers c where c.id=?1")
    Customers findCustomerById(int id);
}
