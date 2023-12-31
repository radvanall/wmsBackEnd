package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.Customer;
import com.warehousemanagement.wms.repository.CustomerRepository;
import com.warehousemanagement.wms.utils.CompareFiles;
import com.warehousemanagement.wms.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    String folder="C:\\Users\\Pc\\Desktop\\js\\prj\\adminend\\admindashboard\\public\\img\\clients\\";
    File avatarImage=new File(folder+"avatar.jpg");
    @Autowired
    private CustomerRepository customerRepository;
    public void setCustomer(List<Customer> customerList) {
        customerRepository.saveAll(customerList);
    }
    public List<Customer> getCustomers(){
        return customerRepository.findByActiveTrue();
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
            ImageHandler imageHandler = new ImageHandler();
            byte[] bytes = file.getBytes();
            if(imgName.isEmpty()){
                imgName="avatar.jpg";
            } else{
                imgName=imageHandler.setImgName(imgName, folder);
                String filePath = folder + imgName;
                Files.write(Paths.get(filePath), bytes);
            }
            String dbFilePath = "/img/clients/" + imgName;
            Customer customer = new Customer(nickname,
                     dbFilePath,
                    email.isEmpty() ? "--" : email,
                    phone,
                    address.isEmpty() ? "--" : address, true);
            customerRepository.save(customer);
            return "Clientul a fost creat cu succes.";
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
    }
    public String updateCustomer(Integer id, String nickname, String email, Integer phone, String imgName, String address, MultipartFile file) throws IOException {
        try {
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(!optionalCustomer.isPresent()) return "Clientul nu a fost gasit";
        Customer customer=optionalCustomer.get();
        String originalImgName=customer.getAvatar().substring(customer.getAvatar().lastIndexOf('/')+1);
        File fileImg=new File(folder+originalImgName);
        String dbFilePath="/img/clients/"+originalImgName;
        String filePath;
        ImageHandler imageHandler=new ImageHandler();
        if(file.isEmpty()){
            if(!originalImgName.equals(imgName) && !originalImgName.equals("avatar.jpg")){
                String newImgName=imageHandler.setImgName(imgName,folder);
                File newFile=new File(folder+newImgName);
                fileImg.renameTo(newFile);
                dbFilePath="/img/clients/"+newImgName;
            }
        }else{
            String newImgName=imageHandler.setImgName(imgName,folder);
            byte[] bytes=file.getBytes();
            filePath=folder+newImgName;
            Files.write(Paths.get(filePath), bytes);
            boolean areEqual= CompareFiles.compareFiles(avatarImage,fileImg);
            if(!areEqual){
                if(!fileImg.delete()) return "Probleme la stergera imaginii vechi";
            }

            dbFilePath="/img/clients/"+newImgName;
        }
        customer.setNickname(nickname);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setAvatar(dbFilePath);
        customerRepository.save(customer);

        return "Clientul a fost editat cu succes";
    }catch (Exception e){
        return "An error occurred: " + e.getMessage();
    }
    }
    public String deleteCustomer(Integer id) {
        try{
            Optional<Customer> optionalCustomer=customerRepository.findById(id);
            if(!optionalCustomer.isPresent()) return "Clientul nu a fost gasit.";
            Customer customer=optionalCustomer.get();
            String originalImgName=customer.getAvatar().substring(customer.getAvatar().lastIndexOf('/')+1);
            File fileImg=new File(folder+originalImgName);
            boolean areEqual= CompareFiles.compareFiles(avatarImage,fileImg);
            if(!areEqual){
                if(!fileImg.delete()) return "Probleme la stergera imaginii vechi";
                customer.setAvatar("/img/clients/avatar.jpg");
            }
            customer.setActive(false);
            customerRepository.save(customer);
            return "Clientul a fost șters";

        }catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
    public List<CustomersSpendingByProduct> getFavoriteProducts(Integer id) {
        PageRequest pageable =  PageRequest.of(0, 10);
        List<CustomersSpendingByProduct> spendingByProducts=customerRepository
                .getCustomerSpendingByProduct(id,pageable);
        return spendingByProducts;
    }
    public ResponseEntity<?> getSales(Integer period) {
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
        List<TotalMoneyDTO> sales=customerRepository.getTotalSales(startDate);
        return ResponseEntity.ok().body(sales);
    }
}
