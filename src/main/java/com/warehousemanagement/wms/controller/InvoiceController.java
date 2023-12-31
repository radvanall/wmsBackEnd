package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.Invoice;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<String> addInvoice(@RequestBody InvoiceDTO invoicesList){
      return  invoiceService.setInvoice(invoicesList);
   }
    @RequestMapping(method = RequestMethod.POST, value="/addOrders")
    public ResponseEntity<String> addNewOrders(@RequestBody List<NewOrderDTO> newOrderDTOS){
        return  invoiceService.addNewOrders(newOrderDTOS);
    }
    @RequestMapping(method = RequestMethod.POST, value="/changeAddress/{id}")
    public ResponseEntity<String> changeAddress(@RequestBody Map<String, String> requestData, @PathVariable Integer id) {
        String newAddress = requestData.get("newAddress");
        return  invoiceService.changeAddress(id,newAddress);
    }
    @RequestMapping(method = RequestMethod.POST, value="/validateInvoice")
    public ResponseEntity<String> validateInvoice(@RequestBody Map<String, Integer> request){
        Integer id=request.get("id");
        return invoiceService.validateInvoice(id);
    }



    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<InvoiceTableDataDTO> getAllInvoice(){
        return invoiceService.getInvoices();
    }
    @RequestMapping(method = RequestMethod.GET, value="/getPendingOrders")
    public List<InvoiceTableDataDTO> getPendingOrders(){
        return invoiceService.getPendingOrders();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public SingleInvoiceDTO getInvoice(@PathVariable Integer id){
        return invoiceService.getInvoice(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateInvoice(@RequestBody Invoice invoice, @PathVariable Integer id){
        invoiceService.updateInvoice(invoice,id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/delete/{id}")
    public ResponseEntity<String> deleteInvoice( @PathVariable Integer id){

       return invoiceService.deleteInvoice(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder (@PathVariable Integer id){
        return invoiceService.deleteOrder(id);
    }
    @RequestMapping(method = RequestMethod.GET,value="/getWeeklySales")
    public List<WeeklySalesDTO> getWeeklySales(@RequestParam("id") Integer id,
                                               @RequestParam("period") Integer period){
        return invoiceService.getWeeklySales(id,period);
    }

}
