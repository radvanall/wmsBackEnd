package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {

    @Modifying
    @Transactional
    @Query( value="INSERT INTO  work.position(name,description,image,unity,min_quantity,subcategory_id,category_id,provider_id,active)" +
            " VALUES (:name,:description,:image,:unity,:minQuantity,:subcategory_id,:category_id,:provider_id,true)",nativeQuery=true)
    void insertPosition(@Param("name") String name,@Param("description") String description,
                        @Param("image") String image,@Param("unity") String unity,
                        @Param("minQuantity") Integer minQuantity,
                        @Param("subcategory_id") Integer subcategory_id,@Param("category_id") Integer category_id,
                        @Param("provider_id")Integer provider_id);
    @Query(nativeQuery=true)
    List<ProductTableDTO>getTableProductsDTOList();

    @Query(nativeQuery=true)
    ProductDTO getProductById(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value="UPDATE work.position SET name=:name,description=:description,image=:image, " +
            "unity=:unity,subcategory_id=:subcategory_id,category_id=:category_id, " +
            "provider_id=:provider_id, min_quantity=:minQuantity " +
            " WHERE id=:id",nativeQuery=true)
    void updatePosition(@Param("id") Integer id,@Param("name") String name,@Param("description") String description,
                        @Param("image") String image,
                        @Param("minQuantity") Integer minQuantity,
                        @Param("unity") String unity,
                        @Param("subcategory_id") Integer subcategory_id,@Param("category_id") Integer category_id,
                        @Param("provider_id")Integer provider_id);
    @Modifying
    @Transactional
     @Query("Update Position set active=false where id=:id")
    void disablePosition(@Param("id")Integer id);
    @Query("SELECT DISTINCT p FROM Position p JOIN p.stocks s WHERE s.state IN ('inSale', 'forSale')")
    List<Position> findPositionsWithSaleStocks();

    @Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
            "date_trunc('week',i.dateOfCreation) AS weekStart,SUM(s.buyingPrice*s.stockQuantity)) FROM Position p " +
            "INNER JOIN p.stocks s " +
            "INNER JOIN s.invoiceReception i " +
            "WHERE p.id=:id "+
            "AND i.dateOfCreation>=:startDate "+
            "GROUP BY weekStart " +
            "ORDER BY weekStart ")
    List<WeeklySalesDTO> getWeeklyAcquisitions(@Param("id") Integer id,@Param("startDate") Date startDate);

    @Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
            "date_trunc('week',i.dateOfCreation) AS weekStart,SUM(s.buyingPrice*s.stockQuantity)) FROM Position p " +
            "INNER JOIN p.stocks s " +
            "INNER JOIN s.invoiceReception i " +
            "WHERE i.dateOfCreation>=:startDate "+
            "GROUP BY weekStart " +
            "ORDER BY weekStart ")
    List<WeeklySalesDTO> getWeeklyAcquisitions(@Param("startDate") Date startDate);
    @Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
            "date_trunc('week',i.date) AS weekStart,SUM(s.sellingPrice*o.quantity) AS totalSales) FROM Position p " +
            "INNER JOIN  p.stocks s " +
            "INNER JOIN s.order o " +
            "INNER JOIN o.invoice i " +
            "WHERE p.id=:id "+
            "AND i.date>=:startDate "+
            "GROUP BY weekStart " +
            "ORDER BY weekStart ")
    List<WeeklySalesDTO> getWeeklySales(@Param("id") Integer id, @Param("startDate") Date startDate);
    @Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
            "date_trunc('week',i.dateOfCreation) AS weekStart,SUM(s.stockQuantity)) FROM Position p " +
            "INNER JOIN p.stocks s " +
            "INNER JOIN s.invoiceReception i " +
            "WHERE i.dateOfCreation>=:startDate "+
            "GROUP BY weekStart " +
            "ORDER BY weekStart ")
    List<WeeklySalesDTO> getWeeklyAcquisitionsQ(@Param("startDate") Date startDate);
    @Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
            "date_trunc('week',i.date) AS weekStart,SUM(o.quantity)) FROM Position p " +
            "INNER JOIN  p.stocks s " +
            "INNER JOIN s.order o " +
            "INNER JOIN o.invoice i " +
            "WHERE i.date>=:startDate "+
            "GROUP BY weekStart " +
            "ORDER BY weekStart ")
    List<WeeklySalesDTO> getWeeklySalesQ( @Param("startDate") Date startDate);
    @Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
            "date_trunc('week',i.date) AS weekStart,SUM(s.sellingPrice*o.quantity) AS totalSales) FROM Position p " +
            "INNER JOIN  p.stocks s " +
            "INNER JOIN s.order o " +
            "INNER JOIN o.invoice i " +
            "WHERE i.date>=:startDate "+
            "GROUP BY weekStart " +
            "ORDER BY weekStart ")
    List<WeeklySalesDTO> getWeeklySales( @Param("startDate") Date startDate);

    @Query("SELECT NEW com.warehousemanagement.wms.dto.RemainingStock( " +
            "p.id,p.image,p.name,SUM(s.remainingQuantity),p.unity) " +
            "FROM Position p " +
            "INNER JOIN p.stocks s " +
            "GROUP BY p.id,p.image,p.name,p.unity " +
            "HAVING SUM(s.remainingQuantity)<=p.minQuantity " +
            "ORDER BY SUM(s.remainingQuantity) ASC")
    List<RemainingStock> getRemainingStocks();

@Query(nativeQuery=true)
    List<TopSalesDTO> getTopSales(@Param("startDate") Date startDate);

@Query("SELECT  NEW com.warehousemanagement.wms.dto.TopSalesDTO( " +
        "p.id,p.name,p.image,date_trunc('week',i.date) AS weekStart,SUM(s.sellingPrice*o.quantity) AS totalSales) FROM Position p " +
        "INNER JOIN  p.stocks s " +
        "INNER JOIN s.order o " +
        "INNER JOIN o.invoice i " +
        "WHERE i.date>=:startDate "+
        "GROUP BY weekStart,p.name,p.id " +
        "ORDER BY weekStart ")
    List<TopSalesDTO> getAllSales(@Param("startDate") Date startDate);

    @Query("SELECT  NEW com.warehousemanagement.wms.dto.TopSalesDTO( " +
            "p.id,p.name,p.image,date_trunc('week',i.dateOfCreation) AS weekStart,SUM(s.buyingPrice*s.stockQuantity)) FROM Position p " +
            "INNER JOIN p.stocks s " +
            "INNER JOIN s.invoiceReception i " +
            "WHERE i.dateOfCreation>=:startDate "+
            "GROUP BY weekStart ,p.id,p.name " +
            "ORDER BY weekStart ")
List<TopSalesDTO> getAllAcquisitions(@Param("startDate") Date startDate);

    @Query("SELECT NEW com.warehousemanagement.wms.dto.ProductWeekBalanceDTO( " +
            "p.name, SUM(s.sellingPrice*o.quantity) as totalSales ) FROM " +
            "Position p " +
            "INNER JOIN p.stocks s " +
            "INNER JOIN s.order o " +
            "INNER JOIN o.invoice i " +
            "WHERE i.date>=CURRENT_DATE - 7  " +
            "GROUP BY p.name " +
            "ORDER BY totalSales")
    List<ProductWeekBalanceDTO> getLastWeekSales();


    @Query("SELECT  NEW com.warehousemanagement.wms.dto.ProductWeekBalanceDTO( " +
            "p.name,SUM(s.buyingPrice*s.stockQuantity) as totalAq) FROM Position p " +
            "INNER JOIN p.stocks s " +
            "INNER JOIN s.invoiceReception i " +
            "WHERE i.dateOfCreation>=CURRENT_DATE - 7  "+
            "GROUP BY p.name " +
            "ORDER BY totalAq ")
    List<ProductWeekBalanceDTO> getLastWeekAcquisitions();
}
