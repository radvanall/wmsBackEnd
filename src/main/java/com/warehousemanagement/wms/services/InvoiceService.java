package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        Invoice invoice=new Invoice();
        invoiceRepository.save(invoice);
        for (InvoiceTableDTO invoiceTableDTO : invoicesList.getInvoiceTableDTOS()){
           Stock stock=stockRepository.findById(invoiceTableDTO.getStockId()).get();
           if(stock.getRemainingQuantity()>=invoiceTableDTO.getQuantity()){
               if(stock.getState().equals("inSale")){
                   stock.setRemainingQuantity(stock.getRemainingQuantity()-invoiceTableDTO.getQuantity());
                   if (stock.getRemainingQuantity()==0) {
                       stock.setState("soldOut");}
                       orders.add(new Order(invoiceTableDTO.getQuantity(),stock,invoice));
                       totalPrice+=invoiceTableDTO.getQuantity()*stock.getSellingPrice();
                       System.out.println("totalPrice:"+totalPrice);

               } else if(stock.getState().equals("forSale")){
                   stock.setRemainingQuantity(stock.getRemainingQuantity()-invoiceTableDTO.getQuantity());
                   if (stock.getRemainingQuantity()==0) {
                       stock.setState("soldOut");
                   }else stock.setState("inSale");
                       orders.add(new Order(invoiceTableDTO.getQuantity(),stock,invoice));
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
             invoice.setAddress(invoicesList.getAddress());
             invoice.setTotalPrice(totalPrice);
             invoice.setShipped(invoicesList.getShipped());
             invoice.setOrder(orders);
             invoice.setCustomer(customer);
             invoice.setOperator(operator);
             invoice.setDate(invoicesList.getDate());
//            Invoice invoice = new Invoice(invoicesList.getAddress(), orders,
//                    totalPrice, invoicesList.getShipped(), operator,invoicesList.getDate(),customer);
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

    public SingleInvoiceDTO getInvoice(Integer id) {
        Invoice invoice=invoiceRepository.findById(id).get();
        SingleInvoiceDTO singleInvoiceDTO=new SingleInvoiceDTO(invoice.getId(),invoice.getOperator().getNickname(),
                invoice.getCustomer().getNickname(),invoice.getCustomer().getAvatar(),
                invoice.getShipped(),invoice.getAddress(),invoice.getDate(),invoice.getTotalPrice(),
                invoice.getOrder().stream().map(order -> {
                    Double totalPrice=order.getQuantity()*order.getStock().getSellingPrice();
                    return new OrderDTO(order.getId(),order.getStock().getId(),
                            order.getStock().getPosition().getId(),
                            order.getStock().getPosition().getName(),
                            order.getStock().getPosition().getImage(),
                            order.getQuantity(),order.getStock().getSellingPrice(),
                            totalPrice,order.getStock().getPosition().getUnity());
                }).collect(Collectors.toList()));

        return singleInvoiceDTO;
    }

    public void updateInvoice(Invoice invoice, Integer id) {
        Invoice getInvoice=invoiceRepository.findById(id).get();
        getInvoice.copyInvoice(invoice);
        invoiceRepository.save(getInvoice);
    }

    public void deleteInvoice(Integer id) {
        invoiceRepository.deleteById(id);
    }


    public ResponseEntity<String> deleteOrder(Integer id) {
        try {
            Order order = orderRepository.findById(id).get();
            Stock stock = order.getStock();
            Invoice invoice = order.getInvoice();
            stock.setRemainingQuantity(order.getQuantity() + stock.getRemainingQuantity());
            if (stock.getRemainingQuantity() == stock.getStockQuantity()) {
                stock.setState("forSale");
            } else stock.setState("inSale");
            Double newTotalPrice = invoice.getTotalPrice() - (Double.valueOf(order.getQuantity()) *
                    order.getStock().getSellingPrice());
            invoice.setTotalPrice(newTotalPrice);
            System.out.println(order.getId());
            System.out.println(invoice.getId());
            System.out.println(stock.getId());
            invoiceRepository.save(invoice);
            stockRepository.save(stock);
            orderRepository.delete(order);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok("Produsul a fost șters");
    }
}
