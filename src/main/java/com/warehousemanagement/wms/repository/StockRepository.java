package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
    @Query("SELECT MAX(stockQuantity) from Stock")
    Integer findMaxQuantity();
    @Query("SELECT MAX(buyingPrice) from Stock")
    Double findMaxBuyingPrice();
    @Query("SELECT MAX(sellingPrice) from Stock")
    Double findMaxSellingPrice();


@Query("SELECT s FROM Stock s " +
        "WHERE (:maxBuyingPrice=0.0 OR s.buyingPrice <= :maxBuyingPrice) " +
        "AND (:minBuyingPrice <= s.buyingPrice) " +
        "AND (:maxSellingPrice=0.0 OR s.sellingPrice <= :maxSellingPrice) " +
        "AND (:minSellingPrice <= s.sellingPrice) " +
        "AND (:maxQuantity=0 OR s.stockQuantity <= :maxQuantity) " +
        "AND (:minQuantity <= s.stockQuantity) " +
        "AND (COALESCE(:providers) IS NULL OR s.position.provider.id IN :providers) " +
        "AND (COALESCE(:categories) IS NULL OR s.position.category.id IN :categories) " +
        "AND (COALESCE(:subcategories) IS NULL OR s.position.subcategory.id IN :subcategories) " +
        "AND (COALESCE(:products) IS NULL OR s.position.id IN :products) " +
        "AND ('allStates' IN :status OR s.state IN :status)")
    Page<Stock> findAllByFilterCriteria(
                                         @Param("providers") List<Integer> providers,
                                          @Param("categories") List<Integer> categories,
                                          @Param("subcategories") List<Integer> subcategories,
                                          @Param("products") List<Integer> products,
                                          @Param("status") List<String> status,
                                          @Param("maxBuyingPrice") Double maxBuyingPrice,
                                          @Param("minBuyingPrice") Double minBuyingPrice,
                                          @Param("maxSellingPrice") Double maxSellingPrice,
                                          @Param("minSellingPrice") Double minSellingPrice,
                                          @Param("maxQuantity") Integer maxQuantity,
                                          @Param("minQuantity") Integer minQuantity,
                                          Pageable pageable);
}
