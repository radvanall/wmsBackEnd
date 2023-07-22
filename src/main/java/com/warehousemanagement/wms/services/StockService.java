package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.StockCardDTO;
import com.warehousemanagement.wms.model.Stock;
import com.warehousemanagement.wms.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public void setStock(List<Stock> stockList) {
      stockRepository.saveAll(stockList);
    }
    public Page<StockCardDTO> getStocks(Integer size, Integer page,String sortDirection) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDirection.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        }
        Sort sortByDateOfCreation=Sort.by(direction,"invoiceReception.dateOfCreation");
        Pageable pageable= PageRequest.of(page,size,sortByDateOfCreation);
        Page<Stock> stockPage = stockRepository.findAll(pageable);
        if (!stockPage.hasContent()) {
            throw new NoSuchElementException("Requested page does not exist");
        }

       List<StockCardDTO> stockCardDTOS=  stockPage.getContent().stream()
                .map(stock -> new StockCardDTO(stock.getId(),
                        stock.getPosition().getName(),
                        stock.getPosition().getImage(),
                        stock.getInvoiceReception().getProvider().getProviderName(),
                        stock.getInvoiceReception().getProvider().getImage(),
                        stock.getStockQuantity(),stock.getRemainingQuantity(),
                        stock.getPosition().getUnity(),
                        stock.getBuyingPrice(),stock.getSellingPrice(),
                        stock.getBuyingPrice()*stock.getStockQuantity(),
                        stock.getSellingPrice()*stock.getStockQuantity(),
                        stock.getInvoiceReception().getDateOfCreation(),
                        stock.getInvoiceReception().getDateOfValidation(),
                        stock.getState(),stock.getInvoiceReception().getId()))
               .collect(Collectors.toList());
         return new PageImpl<>(stockCardDTOS, pageable, stockPage.getTotalElements());
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
        getStock.setRemainingQuantity(quantity);
        stockRepository.save(getStock);
        return "Stocul a fost modificat";
    }

    public String deleteStock(Integer id) {
        stockRepository.deleteById(id);
        return "Stocul a fost șters.";
    }

    public String updateSellingPrice(Integer id, Double sellingPrice) {
        Stock getStock=stockRepository.findById(id).get();
        getStock.setSellingPrice(sellingPrice);
        stockRepository.save(getStock);
        return "Stocul a fost modificat";
    }

    public String updateState(Integer id, String state) {
        Stock getStock=stockRepository.findById(id).get();
        getStock.setState(state);
        stockRepository.save(getStock);
        return "Statutul stocului a fost modificat";
    }
}
