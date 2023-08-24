package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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
                invoice.getCustomer().getAddress(),
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

    public ResponseEntity<String>  deleteInvoice(Integer id) {
        System.out.println(id);
        try {
            Optional<Invoice> optionalInvoice=invoiceRepository.findById(id);
            if(!optionalInvoice.isPresent())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Factura nu a fost găsită");
            Invoice invoice=optionalInvoice.get();
            invoice.getOrder().forEach(order -> {
                Stock stock=order.getStock();
                stock.setRemainingQuantity(order.getQuantity()+stock.getRemainingQuantity());
                stockRepository.save(stock);
            });
            invoiceRepository.delete(invoice);
            return ResponseEntity.ok("Factura a fost ștearsă cu succes");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

        //        invoiceRepository.deleteById(id);
    }


    public ResponseEntity<String> deleteOrder(Integer id) {
        try {
            Order order = orderRepository.findById(id).get();
//            Stock stock = order.getStock();
            List<Stock> stocks=new ArrayList<>();
            Invoice invoice = order.getInvoice();
            List<Order> ordersToDelete=new ArrayList<>();
            invoice.getOrder().forEach(invoiceOrder->{
                if(invoiceOrder.getStock().getPosition().getId()==order.getStock().getPosition().getId() &&
                        invoiceOrder.getId()>=order.getId()) ordersToDelete.add(invoiceOrder);
            });
            ordersToDelete.forEach(orderToDelete->{
                Stock stockToChange=orderToDelete.getStock();
                stockToChange.setRemainingQuantity(orderToDelete.getQuantity()+
                        stockToChange.getRemainingQuantity());
                if (stockToChange.getRemainingQuantity() == stockToChange.getStockQuantity()) {
                    stockToChange.setState("forSale");
                } else stockToChange.setState("inSale");
                stocks.add(stockToChange);
                invoice.setTotalPrice(invoice.getTotalPrice()-(stockToChange.getSellingPrice()*
                        Double.valueOf(orderToDelete.getQuantity())));
            });
            invoiceRepository.save(invoice);
            stocks.forEach(stock->{
                stockRepository.save(stock);
                System.out.println("sTOKS:"+ stock.toString());

            });
            ordersToDelete.forEach(orderToDelete->{
                orderRepository.delete(orderToDelete);
               System.out.println("Orders:"+ orderToDelete.toString());
            });

//            stock.setRemainingQuantity(order.getQuantity() + stock.getRemainingQuantity());
//            if (stock.getRemainingQuantity() == stock.getStockQuantity()) {
//                stock.setState("forSale");
//            } else stock.setState("inSale");
//            Double newTotalPrice = invoice.getTotalPrice() - (Double.valueOf(order.getQuantity()) *
//                    order.getStock().getSellingPrice());
//            invoice.setTotalPrice(newTotalPrice);
//            System.out.println(order.getId());
//            System.out.println(invoice.getId());
//            System.out.println(stock.getId());

//            invoiceRepository.save(invoice);
//            stockRepository.save(stock);
//            orderRepository.delete(order);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok("Produsul a fost șters");
    }

    public ResponseEntity<String> addNewOrders(List<NewOrderDTO> newOrderDTOS) {
        try {
        if(newOrderDTOS.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nu a-ți introdus nici un produs.");
        newOrderDTOS.forEach(order->System.out.println("Order:"+order.toString()));
        Invoice invoice=invoiceRepository.findById(newOrderDTOS.get(0).getInvoiceId()).get();
        List<Stock> stocks=new ArrayList<>();
        List <Order> currentInvoiceOrders=invoice.getOrder();
        List <Order> newOrders=new ArrayList<>();
        Double totalPrice=0.0;
        for (NewOrderDTO orderDTO : newOrderDTOS){
            Boolean isPresent=false;
              for(Order order:currentInvoiceOrders) {
                  if(order.getStock().getId()==orderDTO.getStockId()){
                      if(order.getStock().getRemainingQuantity()>=orderDTO.getQuantity()){
                          totalPrice+=orderDTO.getQuantity()*order.getStock().getSellingPrice();
//                          Stock stock =order.getStock();
//                          stock.setRemainingQuantity(stock.getRemainingQuantity()-orderDTO.getQuantity());
                          order.getStock().setRemainingQuantity(order.getStock().getRemainingQuantity()-orderDTO.getQuantity());
                          if(order.getStock().getRemainingQuantity()==0) order.getStock().setState("soldOut");
                          order.setQuantity(order.getQuantity()+orderDTO.getQuantity());
                          isPresent=true;
                          break;
                      }else  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nu există suficiente bucăți în stocul-"+
                              order.getStock().getPosition().getName());
                  }
              }
              if(!isPresent){
                  Stock stock=stockRepository.findById(orderDTO.getStockId()).get();
                  if (stock.getRemainingQuantity()<orderDTO.getQuantity())
                      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nu există suficiente bucăți în stocul-"+
                          stock.getPosition().getName());
                  stock.setRemainingQuantity(stock.getRemainingQuantity()-orderDTO.getQuantity());
                  if(stock.getRemainingQuantity()==0) stock.setState("soldOut");
                  Order newOrder=new Order(orderDTO.getQuantity(),stock,invoice);
                  stocks.add(stock);
                  newOrders.add(newOrder);
                  totalPrice+=orderDTO.getQuantity()*stock.getSellingPrice();
              }
        }
        invoice.setTotalPrice(invoice.getTotalPrice()+totalPrice);
        stocks.forEach(stock ->
            stockRepository.save(stock)
        );
        newOrders.forEach(order -> orderRepository.save(order));
        invoiceRepository.save(invoice);
        return ResponseEntity.ok("Datele  au fost adaugate");
    }  catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
    }

    public ResponseEntity<String> changeAddress(Integer id, String newAddress) {
        try {
            System.out.println("id:" + id);
            System.out.println("newAddress:" + newAddress);
            Invoice invoice = invoiceRepository.findById(id).get();
            invoice.setAddress(newAddress);
            invoiceRepository.save(invoice);
            return ResponseEntity.ok("Adresa a fost modificata");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

    }

    public ResponseEntity<String> validateInvoice(Integer id) {
        try{
            Optional<Invoice> optionalInvoice=invoiceRepository.findById(id);
            if(!optionalInvoice.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Factura nu a fost găsită");
            }
            Invoice invoice=optionalInvoice.get();
            invoice.setShipped(true);
            invoiceRepository.save(invoice);
            return ResponseEntity.ok("Factura a fost modificată");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public List<WeeklySalesDTO> getWeeklySales(Integer id, Integer period ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -period);
        Date monthsAgo = calendar.getTime();
        List<WeeklySalesDTO> weeklySalesDTOS=invoiceRepository.getWeeklySales(monthsAgo,id);
        return  weeklySalesDTOS;
    }

    public List<InvoiceTableDataDTO> getPendingOrders() {
        List<Invoice> invoices=invoiceRepository.findAll();
//        if(invoices.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nu exista facturi.");
//        }
        List<InvoiceTableDataDTO> invoiceTableDataDTOS=invoices.stream().filter(item->!item.getShipped()).map(invoice ->
                new InvoiceTableDataDTO(invoice.getId(),invoice.getCustomer().getAvatar(),
                        invoice.getCustomer().getNickname(), invoice.getDate(),
                        invoice.getOperator().getNickname(),invoice.getTotalPrice()))
                .collect(Collectors.toList());

        return invoiceTableDataDTOS;

    }
}
