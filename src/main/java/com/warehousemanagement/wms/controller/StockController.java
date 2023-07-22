package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.dto.StockCardDTO;
import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.model.Stock;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addStock(@RequestBody List<Stock> stockList){stockService.setStock(stockList);
    }

    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public Page<StockCardDTO> getAllStock(@RequestParam Integer size,
                                          @RequestParam Integer page,
                                          @RequestParam String sortDirection
                                          ){
        Page<StockCardDTO> stocks=stockService.getStocks(size,page,sortDirection);
        return stocks;
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Stock getStock(@PathVariable Integer id){
        return stockService.getStock(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    public ResponseEntity<?> updateStock(@RequestParam("id") Integer id,
                            @RequestParam("buyingPrice") Double buyingPrice,
                            @RequestParam("sellingPrice") Double sellingPrice,
                            @RequestParam("quantity") Integer quantity
                            ){
       String response= stockService.updateStock(id,buyingPrice,sellingPrice,quantity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/updateSellingPrice")
    public ResponseEntity<?> updateSellingPrice(@RequestParam("id") Integer id,
                                         @RequestParam("sellingPrice") Double sellingPrice

    ){
        String response= stockService.updateSellingPrice(id,sellingPrice);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/updateState")
    public ResponseEntity<?> updateState(@RequestParam("id") Integer id,
                                         @RequestParam("state") String state

    ){
        String response= stockService.updateState(id,state);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.POST, value="/delete")
    public ResponseEntity<?> deleteStock(@RequestBody Map<String, Integer> request){
        Integer id=request.get("id");
        String response=stockService.deleteStock(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
