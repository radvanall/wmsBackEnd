package com.warehousemanagement.wms.repository;


import com.warehousemanagement.wms.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Integer> {
    @Query("SELECT subcategoryName From Subcategory")
    List<String> getSubcategoryName();
    @Query("SELECT new com.warehousemanagement.wms.model.Subcategory(id, subcategoryName) From Subcategory")
    List<Subcategory> getSubcategoryNameAndId();

}
