package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.dto.WeeklySalesDTO;
import com.warehousemanagement.wms.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator,Integer> {
    @Query("SELECT NEW com.warehousemanagement.wms.dto.WeeklySalesDTO(  " +
            "date_trunc('week',i.date) AS weekStart,SUM(i.totalPrice) AS totalSales ) " +
            "FROM Operator o " +
            "INNER JOIN o.invoices i " +
            "WHERE o.id=:id " +
            "AND i.date>=:startDate " +
            "GROUP BY weekStart " +
            "ORDER BY weekStart " )
    List<WeeklySalesDTO> getWeeklySales(@Param("id") Integer id, @Param("startDate") Date startDate);
    Optional<Operator>findByNickname(String nickname);
}
