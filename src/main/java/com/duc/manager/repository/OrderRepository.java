package com.duc.manager.repository;

import com.duc.manager.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    @Query(value = "SELECT sum(total_money)" +
            "from orders",nativeQuery = true)
    double getRevenue();
}
