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

    public void setCategory(List<Category> categoryList) {
            categoryRepository.saveAll(categoryList);
        }
        public List<Category> getCategories(){
            return categoryRepository.findAll();
        }
        public Category getCategory(Integer id){
            return categoryRepository.findById(id).get();
        }
        public void updateCategory(Category category,Integer id){
            Category getCategory=categoryRepository.findById(id).get();
            getCategory.setCategoryName(category.getCategoryName());
            categoryRepository.save(getCategory);
        }

    public void addPosition(Integer positionId, Integer categoryId) {
        Category getCategory=categoryRepository.findById(categoryId).get();
        Position getPosition=positionRepository.findById(positionId).get();
        getCategory.addPositions(getPosition);
        categoryRepository.save(getCategory);
    }
}

