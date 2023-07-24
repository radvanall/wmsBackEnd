package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
    @Query("SELECT MAX(stockQuantity) from Stock")
    Integer findMaxQuantity();
    @Query("SELECT MAX(buyingPrice) from Stock")
    Double findMaxBuyingPrice();
    @Query("SELECT MAX(sellingPrice) from Stock")
    Double findMaxSellingPrice();


}
