package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.PositionRepository;
import com.warehousemanagement.wms.repository.ProviderRepository;
import com.warehousemanagement.wms.utils.FindSumForDate;
import com.warehousemanagement.wms.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
        //return providerRepository.findById(id).get();
    }
//    public void updateProvider(Provider provider,Integer id){
//        Provider getProvider=providerRepository.findById(id).get();
//        getProvider.setProviderName(provider.getProviderName());
//        providerRepository.save(getProvider);
//    }
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
//            provider.setImage(dbFilePath);
        }
    }else{
        String newImgName = imageHandler.setImgName(imgName,folder);
        byte[] bytes = file.getBytes();
        filePath=folder+newImgName;
        Files.write(Paths.get(filePath), bytes);
        dbFilePath="/img/providers/"+newImgName;
//        provider.setImage(dbFilePath);
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

    public String deleteProvider(Integer id) {
        providerRepository.disableProvider(id);
        return  "Furnizorul a fost șters";
    }

    public List<SaleAndAcquisitionDTO> getBalance(Integer id,Integer period) {
        Calendar currentDate = Calendar.getInstance();
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
        List<SaleAndAcquisitionDTO> saleAndAcquisitionDTOS=new ArrayList<>();
        List<WeeklySalesDTO> salesDTOS=providerRepository.getWeeklySales(id,startDate);
        List<WeeklySalesDTO> acquisitionsDTOS= providerRepository.getWeeklyAcquisitions(id,startDate);


        while (currentDate.after(monthsAgo)) {
            Calendar weekStart = (Calendar) currentDate.clone();
            weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            weekStart.set(Calendar.HOUR_OF_DAY, 0);
            weekStart.set(Calendar.MINUTE, 0);
            weekStart.set(Calendar.SECOND, 0);
            Calendar weekEnd = (Calendar) weekStart.clone();
            weekEnd.add(Calendar.DAY_OF_WEEK, 6);
            System.out.println("weekStart="+weekStart.getTime());
            double sumSales = FindSumForDate.findSumForDate(salesDTOS, weekStart.getTime(),weekEnd.getTime());
            double sumAcquisitions = FindSumForDate.findSumForDate(acquisitionsDTOS, weekStart.getTime(),weekEnd.getTime());
            saleAndAcquisitionDTOS.add(new SaleAndAcquisitionDTO(weekStart.getTime(),sumSales,sumAcquisitions));
            currentDate.add(Calendar.WEEK_OF_YEAR, -1);
        }
        return saleAndAcquisitionDTOS;
    }
}
