package com.duc.manager.service;

import com.duc.manager.dto.request.CustomerCreationRequest;
import com.duc.manager.dto.request.ProductCreationRequest;
import com.duc.manager.entity.Customers;
import com.duc.manager.entity.Products;
import com.duc.manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

//    public Customers createCustomer(CustomerCreationRequest request){
//        Customers customer= new Customers();
//
//        customer.setName(request.getName());
//        customer.setEmail(request.getEmail());
//        customer.setPhone(request.getPhone());
//        customer.setAddress(request.getAddress());
//
//        return customerRepository.save(customer);
//    }

    public List<Customers> getCustomers(){
        return customerRepository.findAll();
    }

    public Customers getCustomer(int Id){
        return customerRepository.findById(Id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }



//    public long getQuantityCustomer(){
//        return customerRepository.count();
//    }

    public Customers updateCustomer(int Id, CustomerCreationRequest request){
        Customers customer = getCustomer(Id);
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        return customerRepository.save(customer);

    }

    public List<Customers> getTop5(){
        return customerRepository.getTopCustomers();
    }

    public boolean deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
