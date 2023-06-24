package com.warehousemanagement.wms.controller;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(method = RequestMethod.POST, value="/create")
    public void addCategory(@RequestBody List<Category> categoryList){
        categoryService.setCategory(categoryList);
    }
    @RequestMapping(method = RequestMethod.GET, value="/readAll")
    public List<Category> getAllCategory(){
        return categoryService.getCategories();
    }
    @RequestMapping(method = RequestMethod.GET, value="/read/{id}")
    public Category getCategory(@PathVariable Integer id){
        return categoryService.getCategory(id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/update/{id}")
    public void updateCategory(@RequestBody Category category,@PathVariable Integer id){
        categoryService.updateCategory(category,id);

    }
    @RequestMapping(method = RequestMethod.PUT, value="/addposition/{positionId}/{categoryId}")
    public void addPosition(@PathVariable Integer positionId,@PathVariable Integer categoryId){
        categoryService.addPosition(positionId,categoryId);
    }
}
