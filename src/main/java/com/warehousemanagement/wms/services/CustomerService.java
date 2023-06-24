package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Customer;
import com.warehousemanagement.wms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public void setCustomer(List<Customer> customerList) {
        customerRepository.saveAll(customerList);
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public Customer getCustomer(Integer id){
        return customerRepository.findById(id).get();
    }
    public void updateCustomer(Customer customer,Integer id){
        Customer getCustomer=customerRepository.findById(id).get();
        getCustomer.copyCustomer(customer);
        customerRepository.save(getCustomer);
    }

}
