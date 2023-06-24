package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.services.CategoryService;
import com.warehousemanagement.wms.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addProvider(@RequestBody List<Provider> providerList){
        providerService.setProvider(providerList);
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
    public Provider getProvider(@PathVariable Integer id){
        return providerService.getProvider(id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateProvider(@RequestBody Provider provider,@PathVariable Integer id){
        providerService.updateProvider(provider,id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/addposition/{positionId}/{providerId}")
    public void addPosition(@PathVariable Integer positionId,@PathVariable Integer providerId){
        providerService.addPosition(positionId,providerId);
    }
}
