package com.finalproject.possystem.category.service;

import com.finalproject.possystem.category.entity.Category;
import com.finalproject.possystem.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /*부모가 null인 대분류 category만 반환*/
    public List<Category> getAllMainCategory(){
        return categoryRepository.findByParentIsNull();
    }

    /*특정 부모 id로 자식category리스트를 반환*/
    public List<Category> getSubCategoryByParent(Integer parentId){
        Category parent = categoryRepository.findByCategoryId(parentId);
        return categoryRepository.findByParent(parent);
    }

    /*category insert*/
//    public Category categoryInsert(Category category){
//        return categoryRepository.save(category);
//    }

    // 카테고리 추가
    public Category categoryInsert(Category category) {
        // 부모 설정이 있는 경우 처리
        if (category.getParent() != null) {
            Category parent = categoryRepository.findById(category.getParent().getCategoryId()).orElse(null);
            category.setParent(parent);
        }
        return categoryRepository.save(category); // ID 자동 증가
    }


    /*category update*/
    public Category categoryUpdate(Category category) {
        Category categoryupdate = categoryRepository.findById(category.getCategoryId()).orElse(null);
        if (categoryupdate == null) return null;
        categoryupdate.setParent(category.getParent());
        categoryupdate.setCategoryname(category.getCategoryname());
        categoryupdate.setIsvisible(category.getIsvisible());
        return categoryRepository.save(categoryupdate);
    }

    /*category delete*/
    public void categoryDelete(Integer category_id){
        categoryRepository.deleteById(category_id);
    }
}
