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

    public String updateStock(Integer id,
                            Double buyingPrice,
                            Double sellingPrice,
                            Integer quantity) {
        Stock getStock=stockRepository.findById(id).get();
        getStock.setBuyingPrice(buyingPrice);
        getStock.setSellingPrice(sellingPrice);
        getStock.setStockQuantity(quantity);
        stockRepository.save(getStock);
        return "Stocul a fost modificat";
    }

    public String deleteStock(Integer id) {
        stockRepository.deleteById(id);
        return "Stocul a fost șters.";
    }
}
