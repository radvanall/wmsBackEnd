package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.CustomerInvoiceDTO;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Customer;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.services.CategoryService;
import com.warehousemanagement.wms.services.CustomerService;
import com.warehousemanagement.wms.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addCustomer(@RequestBody List<Customer> operatorsList){
        customerService.setCustomer(operatorsList);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Customer> getAllCustomer(){
        return customerService.getCustomers();
    }
    @RequestMapping(method = RequestMethod.GET, value="/readCustomerInvoice")
    public List<CustomerInvoiceDTO> getAllCustomerInvoice(){

        return customerService.getCustomerInvoice();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Customer getCustomer(@PathVariable Integer id){
        return customerService.getCustomer(id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateCustomer(@RequestBody Customer operator, @PathVariable Integer id){
        customerService.updateCustomer(operator,id);

    }
}
