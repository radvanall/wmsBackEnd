package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Provider;
import com.warehousemanagement.wms.model.Stock;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Stock> getAllStock(){
        return stockService.getStocks();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Stock getStock(@PathVariable Integer id){
        return stockService.getStock(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateStock(@RequestBody Stock stock,@PathVariable Integer id){
        stockService.updateStock(stock,id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/delete")
    public ResponseEntity<?> deleteStock(@RequestBody Map<String, Integer> request){
        Integer id=request.get("id");
        String response=stockService.deleteStock(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
