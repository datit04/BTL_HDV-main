package com.duc.manager.repository;

import com.duc.manager.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends JpaRepository<Customers,Integer> {
    @Query(value = "SELECT * FROM customers ORDER BY customers.name ASC LIMIT 10", nativeQuery = true)
    List<Customers> getTopCustomers();

    // Phân loại khách hàng theo tổng chi tiêu (VIP, Regular)
    @Query(value = """
        SELECT c.customer_id AS customerId, 
               c.name AS name, 
               c.email AS email, 
               SUM(o.total_money) AS totalSpent,
               CASE 
                   WHEN SUM(o.total_money) > 100000 THEN 'VIP'
                   WHEN SUM(o.total_money) BETWEEN 50000 AND 100000 THEN 'Regular'
                   ELSE 'Basic'
               END AS customerType
        FROM customers c
        LEFT JOIN orders o ON c.customer_id = o.customer_id
        GROUP BY c.customer_id, c.name, c.email
        ORDER BY totalSpent DESC;
    """, nativeQuery = true)
    List<Map<String, Object>> classifyCustomersByTotalSpent();


    // . Top 5 sản phẩm được khách hàng mua nhiều nhất
    @Query(value = """
       SELECT\s
                        c.customer_id,\s
                        c.name AS customer_name,\s
                        COUNT(CASE WHEN o.status = 'COMPLETED' THEN 1 END) AS completed_orders,
                        COUNT(o.order_id) AS total_orders,
                        ROUND((COUNT(CASE WHEN o.status = 'COMPLETED' THEN 1 END) * 100.0 / COUNT(o.order_id)), 2) AS completion_rate
                    FROM\s
                        customers c
                    LEFT JOIN\s
                        orders o\s
                    ON\s
                        c.customer_id = o.customer_id
                    GROUP BY\s
                        c.customer_id, c.name
                    ORDER BY\s
                        completion_rate DESC;
            
            
            
            
            
    """, nativeQuery = true)
    List<Map<String, Object>> customerFindOfOne();
}
