package com.warehousemanagement.wms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehousemanagement.wms.dto.InvoiceReceptionDTO;
import com.warehousemanagement.wms.dto.InvoiceReceptionTableDTO;
import com.warehousemanagement.wms.dto.InvoiceStockDTO;
import com.warehousemanagement.wms.model.InvoiceReception;
import com.warehousemanagement.wms.services.InvoiceReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/invoiceReception")
public class InvoiceReceptionController {
    @Autowired
    private InvoiceReceptionService invoiceReceptionService;

    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<?> addInvoice(@RequestParam("data") String  data,
                                        @RequestParam("providerId") Integer providerId,
                                        @RequestParam("adminId") Integer adminId) throws JsonProcessingException {
        System.out.println("data="+data);
        ObjectMapper mapper = new ObjectMapper();
        List<InvoiceStockDTO> invoiceStockDTO = mapper.readValue(data, new TypeReference<List<InvoiceStockDTO>>(){});
        invoiceReceptionService.setInvoiceReception(invoiceStockDTO, providerId, adminId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Factura a fost adăugată");
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<InvoiceReception> getAllInvoiceReception(){
        return invoiceReceptionService.getInvoiceReceptions();
    }
    @RequestMapping(method = RequestMethod.GET, value="/readInvoiceReceptionTable")
    public List<InvoiceReceptionTableDTO> getInvoiceReceptionsTable(){
        return invoiceReceptionService.getInvoiceReceptionsTable();
    }
    @RequestMapping(method = RequestMethod.GET, value="/readInvoiceReceptionTable/{id}")
    public List<InvoiceReceptionTableDTO> getInvoiceReceptionsTable(@PathVariable Integer id){
        return invoiceReceptionService.getInvoiceReceptionsTable(id);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readUnvalidatedInvoiceReceptionTable")
    public List<InvoiceReceptionTableDTO> getUnvalidatedInvoiceReceptionsTable(){
        return invoiceReceptionService.getUnvalidatedInvoiceReceptionsTable();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public InvoiceReceptionDTO getInvoiceReception(@PathVariable Integer id){
        return invoiceReceptionService.getInvoiceReception(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateInvoiceReception(@RequestBody InvoiceReception invoiceReception, @PathVariable Integer id){
        invoiceReceptionService.updateInvoiceReception(invoiceReception,id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/delete/{id}")
    public ResponseEntity<?> deleteInvoiceReception( @PathVariable Integer id){
        String response =invoiceReceptionService.deleteInvoiceReception(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/validate")
    public  ResponseEntity<?> validateInvoice( @RequestBody Map<String, Integer> request){
        Integer id=request.get("id");
        System.out.println("id="+id);
        String response=invoiceReceptionService.validateInvoice(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }
    @RequestMapping(method = RequestMethod.POST, value="/addStock")
    public  ResponseEntity<?> addStock( @RequestBody Map<String, Integer> stock){
        System.out.println("request="+stock);
        String response=invoiceReceptionService.addStock(stock);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

}

