package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.OperatorWorkDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorWorkDaysRepository extends JpaRepository<OperatorWorkDays,Integer> {
}
