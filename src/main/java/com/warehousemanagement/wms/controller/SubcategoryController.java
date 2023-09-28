package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategory")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<?> addSubcategory(@RequestParam String category){
        String response=subcategoryService.setSubcategory(category);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Subcategory> getAllSubcategory(){
        return subcategoryService.getSubcategories();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Subcategory getSubcategory(@PathVariable Integer id){
        return subcategoryService.getSubcategory(id);
    }
    @RequestMapping(method = RequestMethod.POST, value="/update/{id}")
    public ResponseEntity<?>  updateSubcategory(@RequestParam String categoryName,@PathVariable Integer id){
       String response= subcategoryService.updateSubcategory(categoryName,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    public  ResponseEntity<?>  deleteSubcategory(@PathVariable Integer id){
        String response=subcategoryService.deleteSubcategory(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/addposition/{positionId}/{categoryId}")
    public void addPosition(@PathVariable Integer positionId,@PathVariable Integer categoryId){
        subcategoryService.addPosition(positionId,categoryId);
    }
}
