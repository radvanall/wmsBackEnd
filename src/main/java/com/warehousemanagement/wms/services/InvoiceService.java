package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.InvoiceDTO;
import com.warehousemanagement.wms.dto.InvoiceTableDTO;
import com.warehousemanagement.wms.dto.InvoiceTableDataDTO;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<String> setInvoice(InvoiceDTO invoicesList) {
        System.out.println(invoicesList.toString());
        if(invoicesList.getAddress().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Introduce-ți adresa");
        if(invoicesList.getClientId()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alege-ți clientul");
        if(invoicesList.getOperatorId()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operatorul ne e detectat");
        if(invoicesList.getInvoiceTableDTOS().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Factura e goală");

        List<Order> orders=new ArrayList<>();
        List<String> errors=new ArrayList<>();
        Double totalPrice=0.0;
        for (InvoiceTableDTO invoiceTableDTO : invoicesList.getInvoiceTableDTOS()){
           Stock stock=stockRepository.findById(invoiceTableDTO.getStockId()).get();
           if(stock.getRemainingQuantity()>=invoiceTableDTO.getQuantity()){
               if(stock.getState().equals("inSale")){
                   stock.setRemainingQuantity(stock.getRemainingQuantity()-invoiceTableDTO.getQuantity());
                   if (stock.getRemainingQuantity()==0) {
                       stock.setState("soldOut");}
                       orders.add(new Order(invoiceTableDTO.getQuantity(),stock));
                       totalPrice+=invoiceTableDTO.getQuantity()*stock.getSellingPrice();
                       System.out.println("totalPrice:"+totalPrice);

               } else if(stock.getState().equals("forSale")){
                   stock.setRemainingQuantity(stock.getRemainingQuantity()-invoiceTableDTO.getQuantity());
                   if (stock.getRemainingQuantity()==0) {
                       stock.setState("soldOut");
                   }else stock.setState("inSale");
                       orders.add(new Order(invoiceTableDTO.getQuantity(),stock));
                       totalPrice+=invoiceTableDTO.getQuantity()*stock.getSellingPrice();
                       System.out.println("totalPrice:"+totalPrice);

               }else errors.add("Stocul: "+stock.getPosition().getName()+ " nu este pentru vînzare");

           }else errors.add("Nu există suficiente bucăți în stocul: "+stock.getPosition().getName());
       };
        if(!errors.isEmpty()){
            String response=String.join(", ", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            Operator operator = operatorRepository.findById(invoicesList.getOperatorId()).get();
            if (operator == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operator not found");
            }
            Customer customer=customerRepository.findById(invoicesList.getClientId()).get();
            if (customer == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found");
            }
            orders.forEach(order -> {
                stockRepository.save(order.getStock());
                orderRepository.save(order);
            });

            Invoice invoice = new Invoice(invoicesList.getAddress(), orders,
                    totalPrice, invoicesList.getShipped(), operator,invoicesList.getDate(),customer);
            invoiceRepository.save(invoice);
            System.out.println("Invoice:"+invoice.toString());
            System.out.println("invoice:"+invoice);
            return ResponseEntity.ok("Factura a fost creată cu succes");
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    public List<InvoiceTableDataDTO> getInvoices() {
        List<Invoice> invoices=invoiceRepository.findAll();
//        if(invoices.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nu exista facturi.");
//        }
        List<InvoiceTableDataDTO> invoiceTableDataDTOS=invoices.stream().map(invoice ->
                new InvoiceTableDataDTO(invoice.getId(),invoice.getCustomer().getAvatar(),
                       invoice.getCustomer().getNickname(), invoice.getDate(),
                        invoice.getOperator().getNickname(),invoice.getTotalPrice(),invoice.getShipped()))
                .collect(Collectors.toList());

        return invoiceTableDataDTOS;
    }

    public Invoice getInvoice(Integer id) {return invoiceRepository.findById(id).get();
    }

    public void updateInvoice(Invoice invoice, Integer id) {
        Invoice getInvoice=invoiceRepository.findById(id).get();
        getInvoice.copyInvoice(invoice);
        invoiceRepository.save(getInvoice);
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }


}
