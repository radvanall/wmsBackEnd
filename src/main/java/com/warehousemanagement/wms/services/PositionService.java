package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.PositionForSaleDTO;
import com.warehousemanagement.wms.dto.ProductDTO;
import com.warehousemanagement.wms.dto.ProductTableDTO;
import com.warehousemanagement.wms.dto.StockForSale;
import com.warehousemanagement.wms.model.Position;
import com.warehousemanagement.wms.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {
    String folder="C:\\Users\\Pc\\Desktop\\js\\prj\\adminend\\admindashboard\\public\\img\\products\\";
    String[] imgFormats = {"png", "jpg", "jpeg", "web"};
    @Autowired
    private PositionRepository positionRepository;
    public String setImgName(String imgName){
        String[] imgParts = imgName.split("\\.");

         if(imgParts.length>1){
              boolean contains = Arrays.stream(imgFormats).anyMatch(imgParts[imgParts.length-1]
                .toLowerCase()::equals);
              if(!contains) imgName=imgName+".jpg";
       }
        else if(imgParts.length<=1){imgName=imgName+".jpg";}
        File check=new File(folder+imgName);
        if(check.exists()){
            long now = java.time.Instant.now().getEpochSecond();
            imgName=now+imgName;}
        return imgName;
    }


    public void setPosition(List<Position> positionList) {
        positionRepository.saveAll(positionList);
    }

    public List<Position> getPositions() {
        List<Position> positions=positionRepository.findAll();
//        for(Position position:positions){
//            System.out.println("positions="+position.getStocks());
//        }

        return positions;
    }

    public Position getPosition(Integer id) {return  positionRepository.findById(id).get();
    }

     public String updatePosition(MultipartFile file,String imgName,String productName,
                           String category,String subcategory,String provider,
                           String unity, String productDescription,Integer id) throws IOException {

       Position position= positionRepository.findById(id).get();
       String originalImgName=position.getImage().substring(position.getImage().lastIndexOf('/') + 1);
       File fileImg =new File(folder+originalImgName);
       String dbFilePath="/img/products/"+imgName;
       String filePath;
       if(file.isEmpty()){
          if (!originalImgName.equals(imgName)) {
             String newImgName = setImgName(imgName);
             File newFile = new File(folder + newImgName);
             fileImg.renameTo(newFile);
             dbFilePath="/img/products/"+newImgName;
          }
       }else{
          String newImgName = setImgName(imgName);
          byte[] bytes = file.getBytes();
          filePath=folder+newImgName;
         Files.write(Paths.get(filePath), bytes);
         dbFilePath="/img/products/"+newImgName;
     }
       positionRepository.updatePosition(id,productName,productDescription,dbFilePath,unity,
             Integer.parseInt(subcategory),Integer.parseInt(category),Integer.parseInt(provider));
      return "datele au fost modificate cu success";
}
    public void createProduct(Integer id) {
        Position getPosition=positionRepository.findById(id).get();
//        getPosition.createStock();
        positionRepository.save(getPosition);
    }
    public List<ProductTableDTO> getTableProducts(){
        return positionRepository.getTableProductsDTOList();
    }
    public String uploadPosition(MultipartFile file,String imgName,String productName,
                              String category,String subcategory,String provider,
                              String unity, String productDescription) throws IOException {

        byte[] bytes = file.getBytes();
        imgName=setImgName(imgName);
        String dbFilePath="/img/products/"+imgName;
        String filePath=folder+imgName;
        Files.write(Paths.get(filePath), bytes);
        positionRepository.insertPosition(productName,productDescription,dbFilePath,unity,
                Integer.parseInt(subcategory),Integer.parseInt(category),Integer.parseInt(provider));
        if (file!=null){
            return "datele au fost incarcate cu success";
        }
        return null;
    }

       public ProductDTO getProduct(Integer id) throws IOException {
          ProductDTO productDTO=positionRepository.getProductById(id);
          String imgName = productDTO.getImg().substring(productDTO.getImg().lastIndexOf('/') + 1);
          productDTO.setImgName(imgName);
          return  productDTO ;}

    public String deletePosition(Integer id) {
        positionRepository.disablePosition(id);
        return  "Produsul a fost sters";
    }

    public List<PositionForSaleDTO> getPositionsForSale() {
        List<Position> positions=positionRepository. findPositionsWithSaleStocks();
        List<PositionForSaleDTO> positionForSaleDTOS = positions.stream().map((position ->
                new PositionForSaleDTO(position.getId(),position.getName(),position.getImage()
                ,position.getUnity(), position.getStocks().stream()
                        .filter(stock -> "forSale".equals(stock.getState()) || "inSale".equals(stock.getState()))
                        .map(item->new StockForSale(item.getId(),position.getId(),item.getSellingPrice(),
                                item.getRemainingQuantity(),item.getState(),item.getInvoiceReception().getDateOfValidation())).collect(Collectors.toList()))))
                .collect(Collectors.toList());
        return positionForSaleDTOS;

    }
}
