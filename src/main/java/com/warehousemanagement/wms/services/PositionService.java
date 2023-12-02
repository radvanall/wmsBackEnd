package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.Order;
import com.warehousemanagement.wms.model.Position;
import com.warehousemanagement.wms.repository.OrderRepository;
import com.warehousemanagement.wms.repository.PositionRepository;

import com.warehousemanagement.wms.utils.FindSumForDate;
import com.warehousemanagement.wms.utils.SalesAndAcquisitions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PositionService {
    String folder="C:\\Users\\Pc\\Desktop\\js\\prj\\adminend\\admindashboard\\public\\img\\products\\";
    String[] imgFormats = {"png", "jpg", "jpeg", "web"};
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private OrderRepository orderRepository;
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
   public List<ProductsWeeklySalesDTO> getProductWeeklyData(List<TopSalesDTO> topSalesDTOS){
       List<ProductsWeeklySalesDTO> productSales=new ArrayList<>();
       for(TopSalesDTO sale :topSalesDTOS ){
           boolean isPresent=false;
           for(ProductsWeeklySalesDTO productSale:productSales) {
               if (productSale.getId().equals(sale.getId())) {
                   productSale.addSale(new WeeklySalesDTO(sale.getDate(), sale.getWeeklySum()));
                   isPresent = true;
                   break;
               }
           }
           if(!isPresent) {
               List<WeeklySalesDTO> weeklySalesDTO =new ArrayList<>();
               weeklySalesDTO.add(new WeeklySalesDTO(sale.getDate(),sale.getWeeklySum()));
               productSales.add(
                       new ProductsWeeklySalesDTO(
                               sale.getId(),
                               sale.getName(),
                               sale.getAvatar(),
                               weeklySalesDTO
                       )
               );
           }

       }
       return productSales;
   }

    public void setPosition(List<Position> positionList) {
        positionRepository.saveAll(positionList);
    }
    public List<Position> getPositions() {
        List<Position> positions=positionRepository.findAll();
        return positions;
    }
    public Position getPosition(Integer id) {return  positionRepository.findById(id).get();
    }
     public String updatePosition(MultipartFile file,String imgName,String productName,
                           String category,String subcategory,String provider,
                           Integer minQuantity,
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
       positionRepository.updatePosition(id,productName,productDescription,dbFilePath,minQuantity,unity,
             Integer.parseInt(subcategory),Integer.parseInt(category),Integer.parseInt(provider));
      return "datele au fost modificate cu success";
}
    public void createProduct(Integer id) {
        Position getPosition=positionRepository.findById(id).get();
        positionRepository.save(getPosition);
    }
    public List<ProductTableDTO> getTableProducts(){
        return positionRepository.getTableProductsDTOList();
    }
    public String uploadPosition(MultipartFile file,String imgName,String productName,
                              String category,String subcategory,String provider,Integer minQuantity,
                              String unity, String productDescription) throws IOException {

        byte[] bytes = file.getBytes();
        imgName=setImgName(imgName);
        String dbFilePath="/img/products/"+imgName;
        String filePath=folder+imgName;
        Files.write(Paths.get(filePath), bytes);
        positionRepository.insertPosition(productName,productDescription,dbFilePath,unity,
                minQuantity,
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
        List<Position> positions=positionRepository.findPositionsWithSaleStocks();
        List<PositionForSaleDTO> positionForSaleDTOS = positions.stream().map((position ->
                new PositionForSaleDTO(position.getId(),position.getName(),position.getImage()
                ,position.getUnity(), position.getStocks().stream()
                        .filter(stock -> "forSale".equals(stock.getState()) || "inSale".equals(stock.getState()))
                        .map(item->new StockForSale(item.getId(),position.getId(),item.getSellingPrice(),
                                item.getRemainingQuantity(),item.getState(),item.getInvoiceReception().getDateOfValidation())).collect(Collectors.toList()))))
                .collect(Collectors.toList());
        return positionForSaleDTOS;

    }
    public PositionForSaleDTO getPositionByOrderId(Integer id) {
        Order order=orderRepository.findById(id).get();
        Integer stockId=order.getStock().getId();
       Position position= order.getStock().getPosition();
        PositionForSaleDTO positionForSaleDTO=
                new PositionForSaleDTO(position.getId(),position.getName(),position.getImage(),
                        position.getUnity(),position.getStocks().stream()
                .filter(stock->"forSale".equals(stock.getState()) || "inSale".equals(stock.getState()) ||
                        stock.getId()==stockId)
                .map(item->new StockForSale(item.getId(),position.getId(),item.getSellingPrice(),
                item.getRemainingQuantity(),item.getState(),item.getInvoiceReception().getDateOfValidation()))
                        .collect(Collectors.toList()));
        return positionForSaleDTO;

    }
    public List<SaleAndAcquisitionDTO> getBalance(Integer id, Integer period) {
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
        List<WeeklySalesDTO> salesDTOS=positionRepository.getWeeklySales(id,startDate);
        List<WeeklySalesDTO> acquisitionsDTOS= positionRepository.getWeeklyAcquisitions(id,startDate);
        return SalesAndAcquisitions.getSalesAndAcquisitions(monthsAgo,
                salesDTOS,acquisitionsDTOS);

    }
    public ResponseEntity<?> getTotalBalance(Integer period, Integer criteria) {
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
        List<WeeklySalesDTO> salesDTOS=criteria==1?
                positionRepository.getWeeklySalesQ(startDate):
                positionRepository.getWeeklySales(startDate);
        List<WeeklySalesDTO> acquisitionsDTOS=criteria==2?
                positionRepository.getWeeklyAcquisitionsQ(startDate):
                positionRepository.getWeeklyAcquisitions(startDate);
        return ResponseEntity.ok().body(SalesAndAcquisitions.getSalesAndAcquisitions(monthsAgo,
                salesDTOS,acquisitionsDTOS));
    }
    public ResponseEntity<?> getOrders(Integer id) {
        try {
            Optional<Position> optionalPosition = positionRepository.findById(id);
            if (!optionalPosition.isPresent()) return ResponseEntity.badRequest().body("Nu exista asa produs");
            Position position = optionalPosition.get();
            List<PositionOrdersDTO> positionOrdersDTOS = new ArrayList<>();
            position.getStocks().stream().forEach(stock -> {
                if (!stock.getOrder().isEmpty()) {
                    stock.getOrder().stream().forEach(order ->
                            positionOrdersDTOS.add(new PositionOrdersDTO(
                                    order.getInvoice().getId(),
                                    order.getInvoice().getDate(),
                                    order.getInvoice().getCustomer().getNickname(),
                                    order.getQuantity(),
                                    stock.getSellingPrice()
                            )));
                }
            });
            return ResponseEntity.ok(positionOrdersDTOS);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getRemainingStocks() {
        List<RemainingStock> remainingStocks=positionRepository.getRemainingStocks();
        return ResponseEntity.ok().body(remainingStocks);
    }
    public ResponseEntity<?> getTopBalance(Integer period,Integer nrOfPositions) {
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
        List<TopSalesDTO> topSalesDTOS=positionRepository.getAllSales(startDate);
        List<TopSalesDTO> topAcquisitionsDTOS=positionRepository.getAllAcquisitions(startDate);
        topSalesDTOS.forEach(item->
                System.out.println(item.toString()));
        topAcquisitionsDTOS.forEach(item->
                System.out.println(item.toString()));
        List<ProductsWeeklySalesDTO> productSales=getProductWeeklyData(topSalesDTOS);
        List<ProductsWeeklySalesDTO> productAcquisitions=getProductWeeklyData(topAcquisitionsDTOS);
        List<ProductWeeklySalesAndAcquisitions> productBalance=new ArrayList<>();

        for(ProductsWeeklySalesDTO productAcquisition:productAcquisitions){
            for(ProductsWeeklySalesDTO productSale:productSales){
                if(productAcquisition.getId().equals(productSale.getId())){
                    List<SaleAndAcquisitionDTO> balance= SalesAndAcquisitions.getValidSalesAndAcquisitions(monthsAgo,productSale.getSales(),productAcquisition.getSales());
                  productBalance.add(new ProductWeeklySalesAndAcquisitions(productAcquisition.getId(),productAcquisition.getName(),
                          productAcquisition.getAvatar(),
                          balance));

                }
            }
        }
       productBalance.sort((o1, o2) -> {
           Double firstSum=o1.getBalance().stream().mapToDouble(SaleAndAcquisitionDTO::getBalance).sum();
           Double secondSum=o2.getBalance().stream().mapToDouble(SaleAndAcquisitionDTO::getBalance).sum();
           return Double.valueOf(secondSum).compareTo(firstSum);
       });
        List<ProductWeeklySalesAndAcquisitions> top = productBalance.subList(0, Math.min(productBalance.size(), nrOfPositions));
        return ResponseEntity.ok().body(top);
    }
    public ResponseEntity<?> getLastWeekSales() {
      List<ProductWeekBalanceDTO> lastWeekSales=
              positionRepository.getLastWeekSales();
      return ResponseEntity.ok().body(lastWeekSales);
    }

    public ResponseEntity<?> getLastWeekAcquisitions() {
        List<ProductWeekBalanceDTO> lastWeekAq=
                positionRepository.getLastWeekAcquisitions();
        return ResponseEntity.ok().body(lastWeekAq);
    }

}
