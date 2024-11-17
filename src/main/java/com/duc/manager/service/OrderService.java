package com.duc.manager.service;

import com.duc.manager.dto.request.OrderCreationRequest;

import com.duc.manager.entity.Orders;
import com.duc.manager.entity.Products;
import com.duc.manager.repository.OrderRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Orders createOrder(OrderCreationRequest request){
        Orders order= new Orders();
        order.setCustomers(request.getCustomer());
        order.setOrder_date(request.getOrder_date());
        order.setStatus(request.getStatus());
        order.setTotalMoney(request.getTotalMoney());
        return orderRepository.save(order);

    }
    public List<Orders> getOrders(){
        return orderRepository.findAll();
    }

    public Orders getOrder(int Id){
        return orderRepository.findById(Id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public long getQuantityOrders(){
        return orderRepository.count();
    }
    public double getRevenue(){
        return orderRepository.getRevenue();
    }

}
