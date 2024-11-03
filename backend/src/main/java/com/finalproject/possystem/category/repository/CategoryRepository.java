package com.finalproject.possystem.category.repository;

import com.finalproject.possystem.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryId(Integer categoryId);

    List<Category> findCategoryByCategoryname(String categoryname);

    List<Category> findByParent(Category parent);


}
