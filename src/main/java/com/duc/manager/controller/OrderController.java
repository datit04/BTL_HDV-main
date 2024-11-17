package com.duc.manager.controller;
import com.duc.manager.dto.request.CustomerCreationRequest;
import com.duc.manager.dto.request.OrderCreationRequest;
import com.duc.manager.entity.Orders;
import com.duc.manager.service.CustomerService;
import com.duc.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    Orders createOrder(@RequestBody OrderCreationRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping("/getOrders")
    List<Orders> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("getOrder/{Id}")
    Orders getCustomer(@PathVariable("Id") int Id){
        return orderService.getOrder(Id);
    }

    @GetMapping("getRevenue")
    double getRevenue(){
        return orderService.getRevenue();
    }

    @GetMapping("/total_revenue")
    public ResponseEntity<Map<String, Double>> getTotalRevenue() {
        double totalRevenue = orderService.getRevenue();
        Map<String, Double> response = new HashMap<>();
        response.put("totalRevenue", totalRevenue);
        return ResponseEntity.ok(response);
        }

}
