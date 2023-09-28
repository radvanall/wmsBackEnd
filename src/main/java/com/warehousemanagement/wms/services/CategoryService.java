package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Position;
import com.warehousemanagement.wms.repository.CategoryRepository;
import com.warehousemanagement.wms.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PositionRepository positionRepository;

    public String setCategory(String categoryName) {
        try {
            Category category=new Category(categoryName);
            categoryRepository.save(category);
            return "Categoria a fost salvată";
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
        }
        public List<Category> getCategories(){
            return categoryRepository.findAll();
        }
        public Category getCategory(Integer id){
            return categoryRepository.findById(id).get();
        }
        public String updateCategory(String categoryName,Integer id){
        try {
            Category getCategory = categoryRepository.findById(id).get();
            getCategory.setCategoryName(categoryName);
            categoryRepository.save(getCategory);
            return "Categoria a fost modificată.";
        }catch(Exception e){
            return "Erorare " + e.getMessage();
         }
        }

    public void addPosition(Integer positionId, Integer categoryId) {
        Category getCategory=categoryRepository.findById(categoryId).get();
        Position getPosition=positionRepository.findById(positionId).get();
        getCategory.addPositions(getPosition);
        categoryRepository.save(getCategory);
    }

    public String deleteCategory(Integer id) {
        try {
            Category category=categoryRepository.findById(id).get();
           if(!category.getPositions().isEmpty()){
               return "Categoria nu poate fi ștearsă deoarece există produse care fac referință la aceasta.";
           };
           categoryRepository.deleteById(id);
           return "Categoria a fost ștearsă";
        }catch (Exception e){
            return "Probleme la conectare";
        }
    }
}

