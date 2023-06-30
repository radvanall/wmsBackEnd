package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Position;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.repository.PositionRepository;
import com.warehousemanagement.wms.repository.ProviderRepository;
import com.warehousemanagement.wms.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProviderService {
    String folder="C:\\Users\\Pc\\Desktop\\js\\prj\\adminend\\admindashboard\\public\\img\\providers\\";
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

    public String addProvider(String providerName,
                              String email,
                              String tel,
                              String imgName,
                              String address,
                              MultipartFile file) throws IOException {
        System.out.println("providerName="+providerName);
        System.out.println("email="+email);
        System.out.println("tel="+tel);
        System.out.println("imgName="+imgName);
        System.out.println("address="+address);
        System.out.println("file="+file);
        ImageHandler imageHandler=new ImageHandler();
        byte[] bytes=file.getBytes();
        imgName=imageHandler.setImgName(imgName,folder);
        String dbFilePath="/img/providers/"+imgName;
        String filePath=folder+imgName;
        Files.write(Paths.get(filePath), bytes);
        Provider provider=new Provider();
        provider.setProviderName(providerName);
        provider.setAddress(address);
        provider.setEmail(email);
        provider.setImage(dbFilePath);
        provider.setTel(tel);
        providerRepository.save(provider);
        if (file!=null){
            return "datele au fost incarcate cu success";
        }
        return null;
    }
}
