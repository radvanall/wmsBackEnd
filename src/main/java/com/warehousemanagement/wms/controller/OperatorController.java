package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.OperatorTableDTO;
import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.services.AdministratorService;
import com.warehousemanagement.wms.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin("*")
@RestController
@RequestMapping("api/operator")
public class OperatorController {
    @Autowired
    private OperatorService operatorService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addOperator(@RequestBody List<Operator> operatorsList){
        operatorService.setOperator(operatorsList);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Operator> getAllOperator(){
        return operatorService.getOperators();
    }
    @RequestMapping(method = RequestMethod.GET, value="/readOperatorsTable")
    public List<OperatorTableDTO> getAllOperatorTable(){
        return operatorService.getOperatorTableDTOs();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Operator getOperator(@PathVariable Integer id){
        return operatorService.getOperator(id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateOperator(@RequestBody Operator operator, @PathVariable Integer id){
        operatorService.updateOperator(operator,id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/updateInvoices/{operatorId}")
    public void addInvoice(@RequestBody Operator operator, @PathVariable Integer operatorId){
        operatorService.addInvoice(operator,operatorId);
    }

}
