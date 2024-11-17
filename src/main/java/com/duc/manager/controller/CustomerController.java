package com.duc.manager.controller;

import com.duc.manager.dto.request.CustomerCreationRequest;
import com.duc.manager.dto.request.ProductCreationRequest;
import com.duc.manager.entity.Customers;
import com.duc.manager.entity.Products;
import com.duc.manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

//    @PostMapping("/createCustomer")
//    Customers createCustomer(@RequestBody CustomerCreationRequest request){
//        return customerService.createCustomer(request);
//    }

    @GetMapping("/getCustomer")
    List<Customers> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("getCustomer/{Id}")
    Customers getCustomer(@PathVariable("Id") int Id){
        return customerService.getCustomer(Id);
    }

//    @GetMapping("getQuantity")
//    public ResponseEntity<Map<String, Long>> getTotalRevenue() {
//        Long quantity = customerService.getQuantityCustomer();
//        Map<String, Long> response = new HashMap<>();
//        response.put("totalQuantity", quantity);
//        return ResponseEntity.ok(response);
//    }

//    @PutMapping("/updateProduct/{Id}")
//    Customers updateCustomer(@RequestBody CustomerCreationRequest request, @PathVariable int Id){
//        return customerService.updateCustomer(Id,request);
//    }

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

}

