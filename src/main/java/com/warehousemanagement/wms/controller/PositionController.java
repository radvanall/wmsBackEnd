package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.Position;
import com.warehousemanagement.wms.services.PositionService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addPosition(@RequestBody List<Position> positionList){
        positionService.setPosition(positionList);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Position> getAllPositions(){
        return positionService.getPositions();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Position getPosition(@PathVariable Integer id){
        return positionService.getPosition(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/delete/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable Integer id){
        String response=positionService.deletePosition(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
      @RequestMapping(method = RequestMethod.PUT, value="/update")
      public ResponseEntity<?>  updatePosition(@RequestParam("image")MultipartFile file,
                                 @RequestParam("imgName") String imgName,
                                 @RequestParam("product_name") String productName,
                                 @RequestParam("categorie") String category,
                                 @RequestParam("subcategorie")String subcategory,
                                 @RequestParam("provider")String provider,
                                 @RequestParam("minQuantity")Integer minQuantity,
                                 @RequestParam("unity")String unity,
                                 @RequestParam("product_description")String productDescription,
                                 @RequestParam("productId")Integer id) throws IOException {
     String response=  positionService.updatePosition(file,imgName,productName,category,subcategory,provider,minQuantity,unity,productDescription,id);
          return ResponseEntity.status(HttpStatus.OK)
                  .body(response);
     }
    @RequestMapping(method = RequestMethod.POST, value="/newProduct/{id}")
    public void createNewProduct(@PathVariable Integer id){
        positionService.createProduct(id);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/readtablepositions")
    public List<ProductTableDTO>getProductsForTable(){ return positionService.getTableProducts();}
    @RequestMapping(method = RequestMethod.POST,value="/uploadPosition")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file,
                                         @RequestParam("imgName") String imgName,
                                         @RequestParam("product_name") String productName,
                                         @RequestParam("categorie") String category,
                                         @RequestParam("subcategorie")String subcategory,
                                         @RequestParam("provider")String provider,
                                         @RequestParam("minQuantity")Integer minQuantity,
                                         @RequestParam("unity")String unity,
                                         @RequestParam("product_description")String productDescription)throws IOException{
        String uploadImage=positionService.uploadPosition(file,imgName,productName,category,subcategory,provider,minQuantity,unity,productDescription);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @RequestMapping(method=RequestMethod.GET,value="/getPositionsForSale")
    public  List<PositionForSaleDTO> getPositionsForSale(){
        List<PositionForSaleDTO> positionForSaleDTOS=positionService.getPositionsForSale();
        return positionForSaleDTOS;
    }
    @RequestMapping(method=RequestMethod.GET,value="/getPositionsByOrderId/{id}")
    public  PositionForSaleDTO getPositionsForSale(@PathVariable Integer id){
       PositionForSaleDTO positionForSaleDTO=positionService.getPositionByOrderId(id);
        return positionForSaleDTO;
    }
    @RequestMapping(method = RequestMethod.GET,value="/readproduct/{id}")
    public ProductDTO getProduct(@PathVariable  Integer id) throws IOException {
        ProductDTO productDTO=positionService.getProduct(id);
        HttpHeaders headers = new HttpHeaders();
        return productDTO;

    }
    @RequestMapping(method = RequestMethod.GET,value="/getBalance")
    public List<SaleAndAcquisitionDTO> getBalance(@RequestParam("id") Integer id,
                                                  @RequestParam("period") Integer period){
        return positionService.getBalance(id,period);
    }
    @RequestMapping(method = RequestMethod.GET,value="/getOrders/{id}")
    public ResponseEntity<?> getOrders(@PathVariable Integer id){
        return positionService.getOrders(id);
    }

    @RequestMapping(method = RequestMethod.GET,value="/getRemainingStocks")
    public ResponseEntity<?> getRemainingStocks(){
        return positionService.getRemainingStocks();
    }
    @RequestMapping(method = RequestMethod.GET,value="/getTotalBalance")
    public ResponseEntity<?> getTotalBalance (@RequestParam("period") Integer period,
                                                @RequestParam("criteria") Integer criteria){
        return positionService.getTotalBalance(period,criteria);
    }
    @RequestMapping(method = RequestMethod.GET,value="/getTopBalance")
    public ResponseEntity<?> getTopBalance(@RequestParam("period") Integer period,
                                           @RequestParam("nrOfPositions") Integer nrOfPositions){
        return positionService.getTopBalance(period,nrOfPositions);
    }
    @RequestMapping(method = RequestMethod.GET,value="/getLastWeekSales")
    public ResponseEntity<?> getLastWeekBalance(){
        return positionService.getLastWeekSales();
    }
    @RequestMapping(method = RequestMethod.GET,value="/getLastWeekAcquisitions")
    public ResponseEntity<?> getLastWeekAcquisitions(){
        return positionService.getLastWeekAcquisitions();
    }
}
