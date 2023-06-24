package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;
    public void setAdministrator(List<Administrator> administratorsList) {
        administratorRepository.saveAll(administratorsList);
    }
    public List<Administrator> getAdministrators(){
        return administratorRepository.findAll();
    }
    public Administrator getAdministrator(Integer id){
        return administratorRepository.findById(id).get();
    }
    public void updateAdministrator(Administrator administrator,Integer id){
        Administrator getAdministrator=administratorRepository.findById(id).get();
        getAdministrator.copyAdministrator(administrator);
        administratorRepository.save(getAdministrator);
    }
}
