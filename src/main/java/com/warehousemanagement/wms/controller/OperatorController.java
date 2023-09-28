package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.OperatorTableDTO;
import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Operator;
import com.warehousemanagement.wms.services.AdministratorService;
import com.warehousemanagement.wms.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
//@CrossOrigin("*")
@RestController
@RequestMapping("api/operator")
public class OperatorController {
    @Autowired
    private OperatorService operatorService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<?>  addOperator(@RequestParam("nickname")String nickname,
                            @RequestParam("name")String name,
                            @RequestParam("surname")String surname,
                            @RequestParam("email")String email,
                            @RequestParam("phone")Integer phone,
                            @RequestParam("imgName")String imgName,
                            @RequestParam("address")String address,
                            @RequestParam("image") MultipartFile file) throws IOException {
        String response=operatorService.addOperator(nickname,name,surname,email,phone,address,imgName,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Integer id,
                                            @RequestParam("oldPassword")String oldPassword,
                                            @RequestParam("newPassword")String newPassword
    ){
        String response=operatorService.changePassword(id,oldPassword,newPassword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
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
    public ResponseEntity<?> getOperator(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(operatorService.getOperator(id));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        }
    @RequestMapping(method = RequestMethod.GET, value="/getSales")
    public ResponseEntity<?> getSales(@RequestParam("id") Integer id,
                                          @RequestParam("period") Integer period ){
        try {
            return ResponseEntity.ok(operatorService.getSales(id, period));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value="/update/{id}")
    public ResponseEntity<?> updateOperator(@PathVariable Integer id,
            @RequestParam("nickname")String nickname,
    @RequestParam("name")String name,
    @RequestParam("surname")String surname,
    @RequestParam("email")String email,
    @RequestParam("phone")Integer phone,
    @RequestParam("imgName")String imgName,
    @RequestParam("address")String address,
    @RequestParam("image") MultipartFile file) throws IOException{
       String response= operatorService.updateOperator(id,nickname,name,surname,email,phone,address,imgName,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/updateInvoices/{operatorId}")
    public void addInvoice(@RequestBody Operator operator, @PathVariable Integer operatorId){
        operatorService.addInvoice(operator,operatorId);
    }
    @RequestMapping(method = RequestMethod.POST, value="/setWorkedHours")
    public ResponseEntity<?> setWorkedHours(@RequestParam("id") Integer id,
                               @RequestParam("date") String date,
                               @RequestParam("workedHours") Integer workedHours){
        return  operatorService.setWorkedHours(id,date,workedHours);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id)
    {
        System.out.println("op control");
        String response=operatorService.deleteOperator(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
