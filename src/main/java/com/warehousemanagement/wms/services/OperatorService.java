package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.OperatorInvoiceDTO;
import com.warehousemanagement.wms.dto.OperatorTableDTO;
import com.warehousemanagement.wms.dto.SingleOperatorDTO;
import com.warehousemanagement.wms.dto.WorkDaysDTO;
import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Invoice;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.model.OperatorWorkDays;
import com.warehousemanagement.wms.repository.InvoiceRepository;
import com.warehousemanagement.wms.repository.OperatorRepository;
import com.warehousemanagement.wms.utils.CompareDateDMY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;
    public OperatorWorkDays findDay(List<OperatorWorkDays> workDays,Date date){
        for(OperatorWorkDays workDay:workDays){
            if(CompareDateDMY.compareDates(workDay.getData(),date)){
                return workDay;
            }
        }
        return  null;
    }
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
    public SingleOperatorDTO getOperator(Integer id){
        Optional<Operator> optionalOperator = operatorRepository.findById(id);
        if(!optionalOperator.isPresent())
            throw new RuntimeException("Operator not found with id: " + id);
        Operator operator=optionalOperator.get();
        operator.getOperatorWorkDays().stream().forEach(day->
                System.out.println(day.toString()));
        SingleOperatorDTO operatorDTO=new SingleOperatorDTO(
                operator.getId(),
                operator.getNickname(),
                operator.getAvatar(),
                operator.getPhone(),
                operator.getEmail(),
                operator.getAddress(),
                operator.getName(),
                operator.getSurname(),
                operator.getStatus(),
                operator.getOperatorWorkDays().stream().map(day->
                    new WorkDaysDTO(day.getId(),day.getData(),day.getWorkedHours())
                ).collect(Collectors.toList()),
                operator.getInvoices().stream().map(invoice ->
                        new OperatorInvoiceDTO(invoice.getId(),invoice.getDate(),
                                invoice.getCustomer().getNickname(),invoice.getShipped(),invoice.getTotalPrice()))
                .collect(Collectors.toList())
        );
        operatorDTO.getWorkedDays().stream().forEach(day->
                System.out.println(day.toString()));
        return operatorDTO;
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

    public ResponseEntity<?> setWorkedHours(Integer id, String date, Integer workedHours) {
        try {
            Date today=new Date();
            Date newDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            System.out.println("id=" + id);
            System.out.println("date=" + newDate);
            System.out.println("workedHours=" + workedHours);
           System.out.println(CompareDateDMY.compareDates(today,newDate));

            Optional<Operator> optionalOperator=operatorRepository.findById(id);
            if(!optionalOperator.isPresent()) return  ResponseEntity.badRequest().body("Operatorul nu exista");
            Operator operator=optionalOperator.get();
            List<OperatorWorkDays> operatorWorkDays=operator.getOperatorWorkDays();
            OperatorWorkDays foundDay=findDay(operatorWorkDays,newDate );
            System.out.println("foundDate=" + foundDay);
            if(foundDay==null){
                operatorWorkDays.add(new OperatorWorkDays(newDate,workedHours));
            }
            else {
                foundDay.setWorkedHours(workedHours);
            }
            operator.setOperatorWorkDays(operatorWorkDays);
            operatorRepository.save(operator);
//            operatorWorkDays.add(new OperatorWorkDays(newDate,workedHours));
//            operator.setOperatorWorkDays(operator.);
            return ResponseEntity.ok().body("datele au fost adaugate");
        }catch(ParseException e){
            return ResponseEntity.badRequest().body("Data nu e in format corect");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }


    }
}
