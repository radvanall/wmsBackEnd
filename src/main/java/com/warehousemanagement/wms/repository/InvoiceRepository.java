package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.dto.WeeklySalesDTO;
import com.warehousemanagement.wms.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
@Query("SELECT NEW com.warehousemanagement.wms.dto.WeeklySalesDTO(date_trunc('week', i.date) AS weekStart, SUM(i.totalPrice) AS totalSales) " +
        "FROM Invoice i WHERE i.date >= :startDate " +
        "And i.customer.id=:id " +
        "GROUP BY weekStart ORDER BY weekStart")
    List<WeeklySalesDTO> getWeeklySales(@Param("startDate") Date startDate,
                                        @Param("id") Integer id);

}
