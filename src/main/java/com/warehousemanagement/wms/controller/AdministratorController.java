package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.AdministratorDTO;
import com.warehousemanagement.wms.dto.OperatorTableDTO;
import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.services.AdministratorService;
import com.warehousemanagement.wms.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<?>  addAdministrator(@RequestParam("nickname")String nickname,
                                          @RequestParam("name")String name,
                                          @RequestParam("surname")String surname,
                                          @RequestParam("email")String email,
                                          @RequestParam("phone")Integer phone,
                                          @RequestParam("imgName")String imgName,
                                          @RequestParam("address")String address,
                                          @RequestParam("image") MultipartFile file) throws IOException {
        String response=administratorService.addAdministrator(nickname,name,surname,email,phone,address,imgName,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<OperatorTableDTO> getAllAdministrator(){
        return administratorService.getAdministrators();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public ResponseEntity<?> getAdministrator(@PathVariable Integer id){
        return administratorService.getAdministrator(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/update/{id}")
    public ResponseEntity<?> updateAdministrator(@PathVariable Integer id,
                                                 @RequestParam("nickname")String nickname,
                                                 @RequestParam("name")String name,
                                                 @RequestParam("surname")String surname,
                                                 @RequestParam("email")String email,
                                                 @RequestParam("phone")Integer phone,
                                                 @RequestParam("imgName")String imgName,
                                                 @RequestParam("address")String address,
                                                 @RequestParam("password")String password,
                                                 @RequestParam("image") MultipartFile file) throws IOException {
        String response= administratorService.updateAdministrator(id,nickname,name,surname,email,
                   phone,address,password,imgName,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/updateAdmin/{id}")
    public ResponseEntity<?> updateAdministratorFromMain(@PathVariable Integer id,
                                                 @RequestParam("nickname")String nickname,
                                                 @RequestParam("name")String name,
                                                 @RequestParam("surname")String surname,
                                                 @RequestParam("email")String email,
                                                 @RequestParam("phone")Integer phone,
                                                 @RequestParam("imgName")String imgName,
                                                 @RequestParam("address")String address,
                                                 @RequestParam("image") MultipartFile file) throws IOException {
        String response= administratorService.updateAdministrator(id,nickname,name,surname,email,
                phone,address,imgName,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Integer id,
                                            @RequestParam("oldPassword")String oldPassword,
                                            @RequestParam("newPassword")String newPassword
                                            ){
        String response=administratorService.changePassword(id,oldPassword,newPassword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/delete/{id}")
    public ResponseEntity<?> deleteAdministrator(@PathVariable Integer id)
    {
        String response=administratorService.deleteAdministrator(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/setWorkedHours")
    public ResponseEntity<?> setWorkedHours(@RequestParam("id") Integer id,
                                            @RequestParam("date") String date,
                                            @RequestParam("workedHours") Integer workedHours){
        return  administratorService.setWorkedHours(id,date,workedHours);
    }

}

