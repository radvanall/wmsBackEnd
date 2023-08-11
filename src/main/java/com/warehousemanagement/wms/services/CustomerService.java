package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.CustomerInvoiceDTO;
import com.warehousemanagement.wms.dto.SingleCustomerDTO;
import com.warehousemanagement.wms.dto.SingleCustomerInvoice;
import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Customer;
import com.warehousemanagement.wms.repository.CustomerRepository;
import com.warehousemanagement.wms.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    String folder="C:\\Users\\Pc\\Desktop\\js\\prj\\adminend\\admindashboard\\public\\img\\clients\\";
    @Autowired
    private CustomerRepository customerRepository;
    public void setCustomer(List<Customer> customerList) {
        customerRepository.saveAll(customerList);
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public SingleCustomerDTO getCustomer(Integer id){
        Customer customer =customerRepository.findById(id).get();
        SingleCustomerDTO customerDTO=new SingleCustomerDTO(
                customer.getId(),
                customer.getNickname(),
                customer.getAvatar(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getActive(),
                customer.getInvoices().stream().map(invoice ->
                        new SingleCustomerInvoice(invoice.getId(),invoice.getDate(),
                                invoice.getShipped(),invoice.getAddress(),invoice.getTotalPrice(),
                        invoice.getOperator().getNickname())).collect(Collectors.toList()));


        return customerDTO;
    }
    public void updateCustomer(Customer customer,Integer id){
        Customer getCustomer=customerRepository.findById(id).get();
        getCustomer.copyCustomer(customer);
        customerRepository.save(getCustomer);
    }

    public List<CustomerInvoiceDTO> getCustomerInvoice() {
       List<Customer> customers=customerRepository.findAll();
       List<CustomerInvoiceDTO> customerInvoiceDTOS=customers.stream().map(customer ->
               new CustomerInvoiceDTO(customer.getId(),customer.getNickname()
                       ,customer.getAddress(),customer.getAvatar())).collect(Collectors.toList());
       return customerInvoiceDTOS;

    }

    public String addCustomer(String nickname,
                              String email,
                              Integer phone,
                              String imgName,
                              String address,
                              MultipartFile file) throws IOException {
        try {
            System.out.println(nickname + imgName + phone + address + email);
            ImageHandler imageHandler = new ImageHandler();
            byte[] bytes = file.getBytes();
            imgName = imageHandler.setImgName(imgName, folder);
            String dbFilePath = "/img/clients/" + imgName;
            String filePath = folder + imgName;
            Files.write(Paths.get(filePath), bytes);
            Customer customer = new Customer(nickname,
                    dbFilePath.isEmpty() ? "--" : dbFilePath,
                    email.isEmpty() ? "--" : email,
                    phone,
                    address.isEmpty() ? "--" : address, true);
            customerRepository.save(customer);
            return "Clientul a fost creat cu succes.";
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
    }
}
