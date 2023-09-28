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

//    @Query(value="select p.id as id ,p.image as img, p.name as name,c.category_name as categorie,s.subcategory_name as subcategorie," +
//            "pr.provider_name as producator,p.unity as unitate  from work.position p inner join work.provider pr on p.provider_id=pr.id inner join " +
//            "work.subcategory s on p.subcategory_id=s.id inner join work.category c on p.category_id=c.id",nativeQuery=true)

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

//    @Query(value="select p.id as id ,p.image as img, p.name as name,c.category_name as categorie,s.subcategory_name as subcategorie, " +
//            " pr.provider_name as producator,p.unity as unitate,p.description as description  from work.position p inner join work.provider pr on p.provider_id=pr.id inner join \n" +
//            " work.subcategory s on p.subcategory_id=s.id inner join work.category c on p.category_id=c.id where p.id=:id",nativeQuery=true)
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


//    @Query(value="" +
//            "SELECT NEW com.warehousemanagement.wms.dto.TopSalesDTO(t.id,  t.name, t.date, sum(t.sum) AS weekly_sum) " +
//            "FROM ( " +
//            "  SELECT p.id, p.name, date_trunc('week', i.date) AS date, sum(o.quantity * s.selling_price) AS sum " +
//            "  FROM work.position p " +
//            "  INNER JOIN work.stock s ON s.position_id = p.id " +
//            "  INNER JOIN work.order o ON o.stock_id = s.id " +
//            "  INNER JOIN work.invoice i ON i.id = o.invoice_id " +
//            "  GROUP BY p.name, date, i.date,p.id " +
//            ") AS t " +
//            " where t.id in ( " +
//            "SELECT m.id from (SELECT p.id, sum(total_price) as total_price from work.position p inner join " +
//            " work.stock s on s.position_id=p.id " +
//            "inner join work.order o on o.stock_id=s.id " +
//            "inner join work.invoice i on i.id=o.invoice_id " +
//            " group by p.id " +
//            " order by total_price desc limit 2) as m " +
//            " ) " +
//            "GROUP BY t.name, t.date,t.id " +
//            "ORDER BY t.date ;",nativeQuery=true)
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
