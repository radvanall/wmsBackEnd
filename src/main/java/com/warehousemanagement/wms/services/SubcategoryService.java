package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.model.Category;
import com.warehousemanagement.wms.model.Position;
import com.warehousemanagement.wms.model.Subcategory;
import com.warehousemanagement.wms.repository.PositionRepository;
import com.warehousemanagement.wms.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private PositionRepository positionRepository;
    public void setSubcategory(List<Subcategory> subcategory){
        subcategoryRepository.saveAll(subcategory);
    }
    public List<Subcategory> getSubcategories(){
       return subcategoryRepository.findAll();
    }
    public Subcategory getSubcategory(Integer id){
        return subcategoryRepository.findById(id).get();
    }
    public void updateSubcategory(Subcategory subcategory,Integer id){
       Subcategory getSubcategory=subcategoryRepository.findById(id).get();
       getSubcategory.setSubcategoryName(subcategory.getSubcategoryName());
       subcategoryRepository.save(getSubcategory);
    }

    public void addPosition(Integer positionId, Integer subcategoryId) {
        Subcategory getSubcategory=subcategoryRepository.findById(subcategoryId).get();
        Position getPosition=positionRepository.findById(positionId).get();
        getSubcategory.addPositions(getPosition);
        subcategoryRepository.save(getSubcategory);
    }
}
