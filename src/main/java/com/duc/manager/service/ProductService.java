package com.duc.manager.service;

import com.duc.manager.dto.request.ProductCreationRequest;
import com.duc.manager.entity.Products;
import com.duc.manager.entity.TopSellProduct;
import com.duc.manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public Products createProduct(ProductCreationRequest request){
        Products product= new Products();

        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImgFileName(request.getImgFileName());
        product.setStock(request.getStock());

        return productRepository.save(product);

    }

    public List<Products> getProducts(){
        return productRepository.findAll();
    }

    public Products getProduct(int Id){
        return productRepository.findById(Id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public long getQuantityProduct(){
        return productRepository.count();
    }

    public Products updateProducts(int Id,ProductCreationRequest request){
        Products product= getProduct(Id);
        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImgFileName(request.getImgFileName());
        product.setStock(request.getStock());
       return productRepository.save(product);

    }

    public List<Map<String, Object>> getTop5(){
        return productRepository.getTop5();
    }

    public int getProductInMonth(){
        return productRepository.getProductInMonth();
    }

}
