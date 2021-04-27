package com.example.ecommerce.repository;

import com.example.ecommerce.model.entities.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends CrudRepository<Orders, Integer> {
}
