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

@RestController
@RequestMapping("api/invoiceReception")
public class InvoiceReceptionController {
    @Autowired
    private InvoiceReceptionService invoiceReceptionService;
//    @RequestMapping(method = RequestMethod.POST, value="/create")
//    public void addInvoiceReception(@RequestBody List<InvoiceReception> invoiceReceptionsList){
//        invoiceReceptionService.setInvoiceReception(invoiceReceptionsList);
//    }
//@RequestMapping(method = RequestMethod.POST, value="/create")
//public void addInvoice(@RequestBody List<InvoiceStockDTO> invoiceStockDTOList){
//    invoiceReceptionService.setInvoiceReception(invoiceStockDTOList);
//}
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
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public InvoiceReceptionDTO getInvoiceReception(@PathVariable Integer id){
        return invoiceReceptionService.getInvoiceReception(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateInvoiceReception(@RequestBody InvoiceReception invoiceReception, @PathVariable Integer id){
        invoiceReceptionService.updateInvoiceReception(invoiceReception,id);

    }
    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    public void deleteInvoiceReception( @PathVariable Integer id){
        invoiceReceptionService.deleteInvoiceReception(id);
    }

}

