package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.OperatorTableDTO;
import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Invoice;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.repository.InvoiceRepository;
import com.warehousemanagement.wms.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    public void setOperator(List<Operator> operatorsList) {
        operatorRepository.saveAll(operatorsList);
    }
    public List<Operator> getOperators(){
        return operatorRepository.findAll();
    }
    public List<OperatorTableDTO> getOperatorTableDTOs(){
        List<Operator> operators=operatorRepository.findAll();
        List<OperatorTableDTO> operatorTableDTOs=new ArrayList<>();
        for(Operator operator: operators){
            operatorTableDTOs.add(convertToOperatorTableDTO(operator));
        }
        return operatorTableDTOs;
    }
    public Operator getOperator(Integer id){
        return operatorRepository.findById(id).get();
    }
    public void updateOperator(Operator operator,Integer id){
        Operator getOperator=operatorRepository.findById(id).get();
        getOperator.copyOperator(operator);
        operatorRepository.save(getOperator);
    }
    public void addInvoice(Operator operator,Integer id) {
        Operator getOperator=operatorRepository.findById(id).get();
        getOperator.addInvoice(operator.getInvoices().get(0));
        operatorRepository.save(getOperator);
    }
    /////////Convert to  DTO
    public OperatorTableDTO convertToOperatorTableDTO(Operator operator){
        OperatorTableDTO operatorTableDTO=new OperatorTableDTO();
        operatorTableDTO.setId(operator.getId());
        operatorTableDTO.setEmail(operator.getEmail());
        operatorTableDTO.setImg(operator.getAvatar());
        operatorTableDTO.setNickname(operator.getNickname());
        operatorTableDTO.setName(operator.getName());
        operatorTableDTO.setSurname(operator.getSurname());
        operatorTableDTO.setStatus(operator.getStatus());
        operatorTableDTO.setTel(operator.getPhone());

        return operatorTableDTO;
    }
}
