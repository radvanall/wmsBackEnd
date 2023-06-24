package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.AdminWorkDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminWorkDaysRepository extends JpaRepository<AdminWorkDays,Integer> {
}
