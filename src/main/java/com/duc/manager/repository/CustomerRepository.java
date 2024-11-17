package com.duc.manager.repository;

import com.duc.manager.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends JpaRepository<Customers,Integer> {
    @Query(value = "SELECT * FROM customers ORDER BY customers.name ASC LIMIT 10", nativeQuery = true)
    List<Customers> getTopCustomers();

    //chưa làm
    void deleteById(Integer id);
}
