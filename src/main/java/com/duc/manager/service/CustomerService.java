package com.duc.manager.service;

import com.duc.manager.dto.request.CustomerCreationRequest;
import com.duc.manager.entity.Customers;
import com.duc.manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public List<Customers> getCustomers(){
        return customerRepository.findAll();
    }

    public Customers getCustomer(int Id){
        return customerRepository.findById(Id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

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

    // Phân loại khách hàng theo tổng chi tiêu (VIP, Regular)
    public List<Map<String, Object>> classifyCustomers() {
        return customerRepository.classifyCustomersByTotalSpent();
    }
    // . Top 5 sản phẩm được khách hàng mua nhiều nhất
    public List<Map<String, Object>> findPro() {
        return customerRepository.customerFindOfOne();
    }
}
