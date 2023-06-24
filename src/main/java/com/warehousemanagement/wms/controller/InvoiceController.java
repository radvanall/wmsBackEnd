package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Invoice;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addInvoice(@RequestBody List<Invoice> invoicesList){
        invoiceService.setInvoice(invoicesList);
   }

    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Invoice> getAllInvoice(){
        return invoiceService.getInvoices();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Invoice getInvoice(@PathVariable Integer id){
        return invoiceService.getInvoice(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateInvoice(@RequestBody Invoice invoice, @PathVariable Integer id){
        invoiceService.updateInvoice(invoice,id);

    }
    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    public void deleteInvoice( @PathVariable Integer id){
        invoiceService.deleteInvoice(id);
    }
//    @RequestMapping(method = RequestMethod.POST, value="/createInvoice/{operatorId}")
//    public void addInvoice(@RequestBody List<Invoice> invoicesList){
//        invoiceService.setInvoice(invoicesList);
//    }

}
