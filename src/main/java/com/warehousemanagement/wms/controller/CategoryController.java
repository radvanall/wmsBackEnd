package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public ResponseEntity<?> addCategory(@RequestParam String category){
        String response=categoryService.setCategory(category);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Category> getAllCategory(){
        return categoryService.getCategories();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Category getCategory(@PathVariable Integer id){
        return categoryService.getCategory(id);

    }
    @RequestMapping(method = RequestMethod.POST, value="/update/{id}")
    public ResponseEntity<?>  updateCategory(@RequestParam String categoryName,@PathVariable Integer id){
        String response=categoryService.updateCategory(categoryName,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
    public  ResponseEntity<?>  deleteCategory(@PathVariable Integer id){
        String response=categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/addposition/{positionId}/{categoryId}")
    public void addPosition(@PathVariable Integer positionId,@PathVariable Integer categoryId){
        categoryService.addPosition(positionId,categoryId);
    }
}
