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
    public String setSubcategory(String subcategoryName){
        try {
            Subcategory subcategory=new Subcategory(subcategoryName);
            subcategoryRepository.save(subcategory);
            return "Subcategoria a fost salvată";
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
    }
    public List<Subcategory> getSubcategories(){
       return subcategoryRepository.findAll();
    }
    public Subcategory getSubcategory(Integer id){
        return subcategoryRepository.findById(id).get();
    }
    public String updateSubcategory(String subcategoryName,Integer id){
        try {
            Subcategory getSubcategory = subcategoryRepository.findById(id).get();
            getSubcategory.setSubcategoryName(subcategoryName);
            subcategoryRepository.save(getSubcategory);
            return "Subcategoria a fost modificată.";
        }catch(Exception e){
            return "Erorare " + e.getMessage();
        }
    }

    public void addPosition(Integer positionId, Integer subcategoryId) {
        Subcategory getSubcategory=subcategoryRepository.findById(subcategoryId).get();
        Position getPosition=positionRepository.findById(positionId).get();
        getSubcategory.addPositions(getPosition);
        subcategoryRepository.save(getSubcategory);
    }

    public String deleteSubcategory(Integer id) {
        try {
            Subcategory subcategory=subcategoryRepository.findById(id).get();
            if(!subcategory.getPositions().isEmpty()){
                return "Subcategoria nu poate fi ștearsă deoarece există produse care fac referință la aceasta.";
            };
            subcategoryRepository.deleteById(id);
            return "Subcategoria a fost ștearsă";
        }catch (Exception e){
            return "Probleme la conectare";
        }
    }
}
