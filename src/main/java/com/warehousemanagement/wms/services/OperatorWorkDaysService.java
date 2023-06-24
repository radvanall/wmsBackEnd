package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.OperatorWorkDays;
import com.warehousemanagement.wms.repository.OperatorWorkDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorWorkDaysService {
    @Autowired
    private OperatorWorkDaysRepository operatorWorkDaysRepository;
    public void  setOperatorWorkDays(List<OperatorWorkDays> operatorWorkDays){
        operatorWorkDaysRepository.saveAll(operatorWorkDays);
    }
    public List<OperatorWorkDays>getOperatorWorkDays(){return operatorWorkDaysRepository.findAll();}
    public OperatorWorkDays getDay(Integer id){return operatorWorkDaysRepository.findById(id).get();}
    public void updateOperatorWorkDay(OperatorWorkDays operatorWorkDay,Integer id){
        OperatorWorkDays getDay=operatorWorkDaysRepository.findById(id).get();
        getDay.copyDay(operatorWorkDay);
        operatorWorkDaysRepository.save(getDay);
    }
}
