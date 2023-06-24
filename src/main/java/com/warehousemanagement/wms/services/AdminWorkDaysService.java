package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.AdminWorkDays;
import com.warehousemanagement.wms.repository.AdminWorkDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminWorkDaysService {
    @Autowired
    private AdminWorkDaysRepository adminWorkDaysRepository;
    public void setAdminWorkDays(List<AdminWorkDays> adminWorkDays){
        adminWorkDaysRepository.saveAll(adminWorkDays);
    }
    public List<AdminWorkDays>getAdminWorkDays(){return adminWorkDaysRepository.findAll();}
    public AdminWorkDays getDay(Integer id){return adminWorkDaysRepository.findById(id).get();}
    public void updateAdminWorkDay(AdminWorkDays adminWorkDays,Integer id){
        AdminWorkDays getDay = adminWorkDaysRepository.findById(id).get();
        getDay.copyDay(adminWorkDays);
        adminWorkDaysRepository.save(getDay);
    }
}
