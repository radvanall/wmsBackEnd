package com.warehousemanagement.wms.repository;

import com.warehousemanagement.wms.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT categoryName From Category")
    List<String>getCategoryName();
    @Query("SELECT new com.warehousemanagement.wms.model.Category(id, categoryName) From Category")
    List<Category> getCategoryNameAndId();
}
