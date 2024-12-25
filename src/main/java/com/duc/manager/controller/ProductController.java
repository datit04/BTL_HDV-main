package com.duc.manager.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.duc.manager.dto.request.ProductCreationRequest;
import com.duc.manager.entity.Products;
import com.duc.manager.entity.TopSellProduct;
import com.duc.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    Products createProduct(@RequestBody ProductCreationRequest request){
        return productService.createProduct(request);
    }

    @GetMapping("/getProduct")
    List<Products> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("getProduct/{Id}")
    Products getProduct(@PathVariable("Id") int Id){
        return productService.getProduct(Id);
    }

    @GetMapping("getQuantity")
    public ResponseEntity<Map<String, Long>> getTotalRevenue() {
        Long quantity = productService.getQuantityProduct();
        Map<String, Long> response = new HashMap<>();
        response.put("totalQuantity", quantity);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateProduct/{Id}")
    Products updateProduct(@RequestBody ProductCreationRequest request,@PathVariable int Id){
        return productService.updateProducts(Id,request);
    }

    @GetMapping("getTop5")
    public List<Map<String, Object>> getTopProductSales()
    { return productService.getTop5(); }


    @GetMapping("getProductInMonth")
    public ResponseEntity<Map<String,Integer>> getProductInMonth() {
        int quantity = productService.getProductInMonth();
        Map<String, Integer> response = new HashMap<>();
        response.put("numberOfProduct", quantity);
        return ResponseEntity.ok(response);
    }


}
