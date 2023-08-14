package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.CustomerInvoiceDTO;
import com.warehousemanagement.wms.dto.CustomersSpendingByProduct;
import com.warehousemanagement.wms.dto.SingleCustomerDTO;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Customer;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.services.CategoryService;
import com.warehousemanagement.wms.services.CustomerService;
import com.warehousemanagement.wms.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<?> addCustomer(@RequestParam("nickname")String nickname,
                                         @RequestParam("email")String email,
                                         @RequestParam("phone")Integer phone,
                                         @RequestParam("imgName")String imgName,
                                         @RequestParam("address")String address,
                                         @RequestParam("image") MultipartFile file) throws IOException {
        String response= customerService.addCustomer(nickname,email,phone,imgName,address,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
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
    public SingleCustomerDTO getCustomer(@PathVariable Integer id){
        return customerService.getCustomer(id);

    }
    @RequestMapping(method = RequestMethod.POST, value="/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id,
                                            @RequestParam("nickname")String nickname,
                                            @RequestParam("email")String email,
                                            @RequestParam("phone")Integer phone,
                                            @RequestParam("imgName")String imgName,
                                            @RequestParam("address")String address,
                                            @RequestParam("image") MultipartFile file) throws IOException {
        String response=  customerService.updateCustomer(id,nickname,email,phone,imgName,address,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id)
    {
        String response=customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.GET,value="/getFavoriteProducts/{id}")
    public List<CustomersSpendingByProduct> getFavoriteProducts(@PathVariable Integer id){
        return customerService.getFavoriteProducts(id);
    }

}
