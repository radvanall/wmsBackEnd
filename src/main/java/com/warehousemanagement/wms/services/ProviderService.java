package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.PositionRepository;
import com.warehousemanagement.wms.repository.ProviderRepository;
import com.warehousemanagement.wms.utils.FindSumForDate;
import com.warehousemanagement.wms.utils.ImageHandler;
import com.warehousemanagement.wms.utils.SalesAndAcquisitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
        return providerRepository.findByActiveTrue();
    }
    public List<Provider> getProvidersNameAndId(){
        return providerRepository.getProviderNameAndId();
    }
    public ProviderDTO getProvider(Integer id){
        Provider provider=providerRepository.findById(id).get();
        ProviderDTO providerDTO=
                new ProviderDTO(provider.getId(),provider.getProviderName(),
                        provider.getEmail(),provider.getTel(),
                        provider.getAddress(),provider.getImage(),
                        provider.getPositions().stream()
                                .map(position -> new PositionDTO(position.getId(),position.getName(),
                                        position.getImage(),position.isActive(),position.getStocks().stream()
                                        .mapToInt(Stock::getRemainingQuantity).sum())).collect(Collectors.toList()),
                        provider.getInvoiceReceptions().stream()
                            .map(invoice->new ProviderPageInvoiceDTO(invoice.getId(),invoice.getDateOfCreation(),
                                    invoice.getCreatedBy().getNickname(),invoice.getStocks().stream()
                            .mapToDouble(stock -> stock.getBuyingPrice() * stock.getStockQuantity()).sum(),invoice.getValidated())).collect(Collectors.toList()));
        return providerDTO;
    }
public String updateProvider(Integer id,String providerName,
                           String email,
                           String tel,
                           String imgName,
                           String address,
                           MultipartFile file ) throws IOException{
    Provider provider=providerRepository.findById(id).get();
    String originalImgName=provider.getImage().substring(provider.getImage().lastIndexOf('/') + 1);
    File fileImg =new File(folder+originalImgName);
    String dbFilePath="/img/providers/"+imgName;
    String filePath;
    ImageHandler imageHandler=new ImageHandler();
    if(file.isEmpty()){
        if (!originalImgName.equals(imgName)) {
            String newImgName = imageHandler.setImgName(imgName,folder);
            File newFile = new File(folder + newImgName);
            fileImg.renameTo(newFile);
            dbFilePath="/img/providers/"+newImgName;
        }
    }else{
        String newImgName = imageHandler.setImgName(imgName,folder);
        byte[] bytes = file.getBytes();
        filePath=folder+newImgName;
        Files.write(Paths.get(filePath), bytes);
        dbFilePath="/img/providers/"+newImgName;
    }
    provider.setProviderName(providerName);
    provider.setEmail(email);
    provider.setTel(tel);
    provider.setAddress(address);
    provider.setImage(dbFilePath);

    providerRepository.save(provider);
    return "datele au fost modificate cu success";
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

    public String deleteProvider(Integer id) {
        providerRepository.disableProvider(id);
        return  "Furnizorul a fost șters";
    }

    public List<SaleAndAcquisitionDTO> getBalance(Integer id,Integer period) {
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
        List<WeeklySalesDTO> salesDTOS=providerRepository.getWeeklySales(id,startDate);
        List<WeeklySalesDTO> acquisitionsDTOS= providerRepository.getWeeklyAcquisitions(id,startDate);
        return SalesAndAcquisitions.getSalesAndAcquisitions(monthsAgo,
                salesDTOS,acquisitionsDTOS);
    }

    public ResponseEntity<?> getTotalBalance(Integer period) {
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
          List<TotalMoneyDTO> sales=providerRepository.getTotalSales(startDate);
          List<TotalMoneyDTO> acquisitions=providerRepository.getTotalAcquisitions(startDate);
          List<ProviderSalesAndAcquisitionsDTO> balance=new ArrayList<>();
          sales.forEach(sale-> acquisitions.forEach(acq->{
              if(sale.getId()==acq.getId())
                  balance.add(new ProviderSalesAndAcquisitionsDTO(sale.getId(),sale.getImage(),sale.getName(),
                          sale.getSum(),acq.getSum()));
          }));
          acquisitions.forEach(acq->{
             ProviderSalesAndAcquisitionsDTO first= balance.stream().filter(item->item.getId().equals(acq.getId())).findFirst().orElse(null);
              if(first==null &&acq.getSum()!=0.0){
                  balance.add(new ProviderSalesAndAcquisitionsDTO(acq.getId(),acq.getImage(),acq.getName(), 0.0,acq.getSum()));
              }
          });
          balance.sort((o1,o2)->
              o2.getBalance().compareTo(o1.getBalance())
          );

        return ResponseEntity.ok().body(balance);
    }
}
