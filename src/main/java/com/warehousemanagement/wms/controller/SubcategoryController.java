package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategory")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addSubcategory(@RequestBody List<Subcategory> subcategoryList){
        subcategoryService.setSubcategory(subcategoryList);

    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Subcategory> getAllSubcategory(){
        return subcategoryService.getSubcategories();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Subcategory getSubcategory(@PathVariable Integer id){
        return subcategoryService.getSubcategory(id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateSubcategory(@RequestBody Subcategory subcategory,@PathVariable Integer id){
         subcategoryService.updateSubcategory(subcategory,id);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/addposition/{positionId}/{categoryId}")
    public void addPosition(@PathVariable Integer positionId,@PathVariable Integer categoryId){
        subcategoryService.addPosition(positionId,categoryId);
    }
}
