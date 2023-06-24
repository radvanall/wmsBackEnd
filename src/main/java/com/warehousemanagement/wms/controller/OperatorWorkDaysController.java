package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.OperatorWorkDays;
import com.warehousemanagement.wms.services.OperatorWorkDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/operatorWorkDays")
public class OperatorWorkDaysController {
    @Autowired
    private OperatorWorkDaysService operatorWorkDaysService;
    @RequestMapping(method = RequestMethod.POST,value="/create")
    public void addOperatorWorkDays(@RequestBody List<OperatorWorkDays> operatorWorkDays){
        operatorWorkDaysService.setOperatorWorkDays(operatorWorkDays);
    }
    @RequestMapping(method = RequestMethod.GET,value="/readAll")
    public List<OperatorWorkDays> getAllDays(){return operatorWorkDaysService.getOperatorWorkDays();}
    @RequestMapping(method = RequestMethod.GET,value="/read/{id}")
    public OperatorWorkDays getOperatorWorkDay(@PathVariable Integer id){
        return operatorWorkDaysService.getDay(id);
    }
    @RequestMapping(method = RequestMethod.PUT,value="/update/{id}")
    public void updateOperatorWorkDays(@RequestBody OperatorWorkDays operatorWorkDays,@PathVariable Integer id){
        operatorWorkDaysService.updateOperatorWorkDay(operatorWorkDays,id);
    }

}
