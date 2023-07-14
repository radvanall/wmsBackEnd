package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Administrator;
import com.warehousemanagement.wms.model.Stock;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void setStock(List<Stock> stockList) {
      stockRepository.saveAll(stockList);
    }
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public Stock getStock(Integer id) {
        return stockRepository.findById(id).get();
    }

    public void updateStock(Stock stock, Integer id) {
        Stock getStock=stockRepository.findById(id).get();
        getStock.copyStock(stock);
        stockRepository.save(getStock);
    }

    public String deleteStock(Integer id) {
        stockRepository.deleteById(id);
        return "Stocul a fost șters.";
    }
}
