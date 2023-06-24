package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Position;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.repository.PositionRepository;
import com.warehousemanagement.wms.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private PositionRepository positionRepository;

    public void setProvider(List<Provider> categoryList) {
        providerRepository.saveAll(categoryList);
    }
    public List<Provider> getProviders(){
        return providerRepository.findAll();
    }
    public List<Provider> getProvidersNameAndId(){
        return providerRepository.getProviderNameAndId();
    }
    public Provider getProvider(Integer id){
        return providerRepository.findById(id).get();
    }
    public void updateProvider(Provider provider,Integer id){
        Provider getProvider=providerRepository.findById(id).get();
        getProvider.setProviderName(provider.getProviderName());
        providerRepository.save(getProvider);
    }

    public void addPosition(Integer positionId, Integer providerId) {
        Provider getProvider=providerRepository.findById(providerId).get();
        Position getPosition=positionRepository.findById(positionId).get();
        getProvider.addPositions(getPosition);
        providerRepository.save(getProvider);
    }
}
