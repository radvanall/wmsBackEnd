package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.ProviderDTO;
import com.warehousemanagement.wms.dto.SaleAndAcquisitionDTO;
import com.warehousemanagement.wms.dto.WeeklySalesDTO;
import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.services.CategoryService;
import com.warehousemanagement.wms.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<?>addProvider(@RequestParam("providerName")String providerName,
                                        @RequestParam("email")String email,
                                        @RequestParam("tel")String tel,
                                        @RequestParam("imgName")String imgName,
                                        @RequestParam("address")String address,
                                        @RequestParam("image") MultipartFile file) throws IOException {
        String response=providerService.addProvider(providerName,email,tel,imgName,address,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Provider> getAllProviders(){
        return providerService.getProviders();
    }
    @RequestMapping(method = RequestMethod.GET, value="/readProvidersNamesAndId")
    public List<Provider> getAllProvidersNameAndId(){
        return providerService.getProvidersNameAndId();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public ProviderDTO getProvider(@PathVariable Integer id){
        return providerService.getProvider(id);

    }
@RequestMapping(method = RequestMethod.POST, value="/update/{id}")
public ResponseEntity<?> updateProvider(@PathVariable Integer id,
        @RequestParam("providerName")String providerName,
                                        @RequestParam("email")String email,
                                        @RequestParam("tel")String tel,
                                        @RequestParam("imgName")String imgName,
                                        @RequestParam("address")String address,
                                        @RequestParam("image") MultipartFile file) throws IOException {

    String response=providerService.updateProvider(id,providerName,email,tel,imgName,address,file);
    return ResponseEntity.status(HttpStatus.OK)
            .body(response);
}
    @RequestMapping(method = RequestMethod.PUT, value="/delete/{id}")
     public ResponseEntity<?> deleteProvider(@PathVariable Integer id)
    {
        String response=providerService.deleteProvider(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/addposition/{positionId}/{providerId}")
    public void addPosition(@PathVariable Integer positionId,@PathVariable Integer providerId){
        providerService.addPosition(positionId,providerId);
    }
    @RequestMapping(method = RequestMethod.GET,value="/getBalance")
    public List<SaleAndAcquisitionDTO> getBalance(@RequestParam("id") Integer id,
                                                  @RequestParam("period") Integer period){
        return providerService.getBalance(id,period);
    }
    @RequestMapping(method = RequestMethod.GET,value="/getTotalBalance")
    public ResponseEntity<?> getTotalBalance(@RequestParam("period") Integer period){
        return providerService.getTotalBalance(period);
    }
}
