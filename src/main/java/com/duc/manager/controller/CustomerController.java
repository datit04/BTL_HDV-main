package com.duc.manager.controller;

import com.duc.manager.dto.request.CustomerCreationRequest;
import com.duc.manager.entity.Customers;
import com.duc.manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;



    @GetMapping("/getCustomers")
    List<Customers> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("getCustomer/{Id}")
    Customers getCustomer(@PathVariable("Id") int Id){
        return customerService.getCustomer(Id);
    }


    @PutMapping("/updateCustomer/{Id}")
    Customers updateCustomer(@RequestBody CustomerCreationRequest request, @PathVariable int Id){
        return customerService.updateCustomer(Id,request);
    }

    @GetMapping("getCT")
    public List<Customers> getTopProductSales()
    { return customerService.getTop5(); }

    @DeleteMapping("deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        boolean isDeleted = customerService.deleteCustomer(id);
        if (isDeleted) {
            return ResponseEntity.ok("Customer deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }
    // Phân loại khách hàng theo tổng chi tiêu (VIP, Regular)
    @GetMapping("/classification")
    public List<Map<String, Object>> getCustomerClassification() {
        return customerService.classifyCustomers();
    }

    // Tìm khách hàng gần đây nhất có đơn đặt hàng
    @GetMapping("/classification2")
    public List<Map<String, Object>> getFindPro() {
        return customerService.findPro();
    }


}

