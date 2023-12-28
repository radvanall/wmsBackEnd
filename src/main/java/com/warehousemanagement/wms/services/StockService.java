package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.*;
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
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public void setStock(List<Stock> stockList) {
      stockRepository.saveAll(stockList);
    }
    public Page<StockCardDTO> getStocks(Integer size, Integer page, String sortDirection, StockFilterCriteriaDTO filterCriteriaDTO) {

        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDirection.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        }
        Sort sortByDateOfCreation=Sort.by(direction,"invoiceReception.dateOfCreation");
        Pageable pageable= PageRequest.of(page,size,sortByDateOfCreation);
        Page<Stock> stockPage;
        if(filterCriteriaDTO==null){
            stockPage=stockRepository.findAll(pageable);
        }
        else{
        stockPage=stockRepository.findAllByFilterCriteria(
                filterCriteriaDTO.getProviders(),
                filterCriteriaDTO.getCategories(),
                filterCriteriaDTO.getSubcategories(),
                filterCriteriaDTO.getProducts(),
                filterCriteriaDTO.getStatus(),
                filterCriteriaDTO.getMaxBuyingPrice(),
                filterCriteriaDTO.getMinBuyingPrice(),
                filterCriteriaDTO.getMaxSellingPrice(),
                filterCriteriaDTO.getMinSellingPrice(),
                filterCriteriaDTO.getMaxQuantity(),
                filterCriteriaDTO.getMinQuantity(),
                pageable
        );
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
                        stock.getState(),
                        stock.getPosition().getCategory().getCategoryName(),
                        stock.getPosition().getSubcategory().getSubcategoryName(),
                        stock.getInvoiceReception().getId()))
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

    public StockFilterFieldsDTO getStocksFilterFields() {
        List<Provider> providers= providerRepository.findAll();
        List<FilterProviderDTO> filterProviderDTOS = providers.stream()
                .map(provider -> new FilterProviderDTO(
                        provider.getId(),
                        provider.getProviderName(),
                        provider.getPositions().stream()
                                .map(position -> position.getId())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
        List<Position> positions= positionRepository.findAll();
        List<FilterProductDTO> productDTOS=positions.stream()
                .map(position -> new FilterProductDTO(position.getId(),position.getName()))
                .collect(Collectors.toList());
        List<Category> categories=categoryRepository.findAll();
        List<FilterCategoryDTO> categoryDTOS=categories.stream()
                .map(category -> new FilterCategoryDTO(category.getId(),category.getCategoryName(),
                        category.getPositions().stream().map(position -> position.getId())
                        .collect(Collectors.toList()))).collect(Collectors.toList());
        List<Subcategory> subcategories=subcategoryRepository.findAll();
        List<FilterSubcategoryDTO> filterSubcategoryDTOS=subcategories.stream()
                .map(subcategory -> new FilterSubcategoryDTO(subcategory.getId(),
                        subcategory.getSubcategoryName(),subcategory.getPositions().stream().map(position ->
                        position.getId()).collect(Collectors.toList())))
                .collect(Collectors.toList());
        Integer maxQuantity=stockRepository.findMaxQuantity();
        Double maxSellingPrice=stockRepository.findMaxSellingPrice();
        Double maxBuyingPrice=stockRepository.findMaxBuyingPrice();
        StockFilterFieldsDTO stockFilterFieldsDTO=new StockFilterFieldsDTO(
           filterProviderDTOS,productDTOS,categoryDTOS,filterSubcategoryDTOS,maxBuyingPrice,maxSellingPrice,maxQuantity
        );
        return stockFilterFieldsDTO;

    }

    public List<StockCardDTO> getStocksByProductId(Integer id) {
        List<Stock> stocks=stockRepository.findStocksByPositionId(id);
        List<StockCardDTO> stockCardDTOS=stocks.stream().map(stock ->
            new StockCardDTO(stock.getId(),stock.getPosition().getName(),stock.getPosition().getImage(),
                    stock.getPosition().getProvider().getProviderName(),stock.getPosition().getProvider().getImage(),
                    stock.getStockQuantity(),stock.getRemainingQuantity(),stock.getPosition().getUnity(),
                    stock.getBuyingPrice(),stock.getSellingPrice(),stock.getBuyingPrice()*stock.getStockQuantity(),
                    stock.getSellingPrice()*stock.getStockQuantity(), stock.getInvoiceReception().getDateOfCreation(),
                    stock.getInvoiceReception().getDateOfValidation(),
                    stock.getState(),
                    stock.getPosition().getCategory().getCategoryName(),
                    stock.getPosition().getSubcategory().getSubcategoryName(),
                    stock.getInvoiceReception().getId())
        ).collect(Collectors.toList());
        return stockCardDTOS;
    }
}
