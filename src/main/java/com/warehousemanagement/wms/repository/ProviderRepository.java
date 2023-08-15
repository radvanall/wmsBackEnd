package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.dto.WeeklySalesDTO;
import com.warehousemanagement.wms.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    @Query("SELECT providerName From Provider ")
    List<String> getProviderName();
    @Query("SELECT new com.warehousemanagement.wms.model.Provider(id,providerName) From Provider ")
    List<Provider> getProviderNameAndId();

    @Modifying
    @Transactional
    @Query("Update Provider set active=false where id=:id")
    void disableProvider(@Param("id")Integer id);
    List<Provider> findByActiveTrue();

@Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
        "date_trunc('week',i.dateOfCreation) AS weekStart,SUM(s.buyingPrice*s.stockQuantity)) FROM Provider p " +
        "INNER JOIN p.invoiceReceptions i " +
        "INNER JOIN i.stocks s " +
        "WHERE p.id=:id "+
        "AND i.dateOfCreation>=:startDate "+
        "GROUP BY weekStart " +
        "ORDER BY weekStart ")
List<WeeklySalesDTO> getWeeklyAcquisitions(@Param("id") Integer id,@Param("startDate") Date startDate);
    @Query("SELECT  NEW com.warehousemanagement.wms.dto.WeeklySalesDTO( " +
            "date_trunc('week',i.date) AS weekStart,SUM(i.totalPrice) AS totalSales) FROM Provider pr " +
            "INNER JOIN pr.positions p " +
            "INNER JOIN  p.stocks s " +
            "INNER JOIN s.order o " +
            "INNER JOIN o.invoice i " +
            "WHERE pr.id=:id "+
            "AND i.date>=:startDate "+
            "GROUP BY weekStart " +
            "ORDER BY weekStart ")
    List<WeeklySalesDTO> getWeeklySales(@Param("id") Integer id,@Param("startDate") Date startDate);
}
