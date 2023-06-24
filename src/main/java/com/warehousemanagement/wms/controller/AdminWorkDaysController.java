package com.warehousemanagement.wms.controller;


import com.warehousemanagement.wms.model.AdminWorkDays;
import com.warehousemanagement.wms.services.AdminWorkDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/adminWorkDays")
public class AdminWorkDaysController {
    @Autowired
    private AdminWorkDaysService adminWorkDaysService;
    @RequestMapping(method = RequestMethod.POST,value = "/create")
    public void addAdminWorkDays(@RequestBody List<AdminWorkDays> adminWorkDays){
        adminWorkDaysService.setAdminWorkDays(adminWorkDays);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/readAll")
    public List<AdminWorkDays> getAllDays(){return adminWorkDaysService.getAdminWorkDays();}
    @RequestMapping(method = RequestMethod.GET,value = "/read/{id}")
    public AdminWorkDays getAdminWorkDay(@PathVariable Integer id){
        return adminWorkDaysService.getDay(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateAdminWorkDays(@RequestBody AdminWorkDays adminWorkDays,@PathVariable Integer id){
        adminWorkDaysService.updateAdminWorkDay(adminWorkDays,id);
    }
}
