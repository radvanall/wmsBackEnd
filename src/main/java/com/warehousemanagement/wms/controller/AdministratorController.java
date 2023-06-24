package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.services.AdministratorService;
import com.warehousemanagement.wms.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addAdministrator(@RequestBody List<Administrator> administratorsList){
        administratorService.setAdministrator(administratorsList);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Administrator> getAllAdministrator(){
        return administratorService.getAdministrators();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Administrator getAdministrator(@PathVariable Integer id){
        return administratorService.getAdministrator(id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateAdministrator(@RequestBody Administrator administrator, @PathVariable Integer id){
        administratorService.updateAdministrator(administrator,id);

    }
}
