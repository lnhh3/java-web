package com.learning.java_web.services.category;

import com.learning.java_web.models.entities.Category;
import com.learning.java_web.models.requests.CategoryRequest;
import com.learning.java_web.models.requests.UpdateCategoryRequest;
import com.learning.java_web.models.responses.TreeCategoryResponse;
import com.learning.java_web.repositories.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    ICategoryRepo categoryRepo;

    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public List<TreeCategoryResponse> getTreeCategories() {
        return null;
    }

    @Override
    public List<TreeCategoryResponse> getTreeCategoriesById(String id) {
        return null;
    }

    @Override
    public List<Category> getCategoriesByParentId(String parentId) {
        return null;
    }

    @Override
    public Category getCategoryById(String id) {
        return null;
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public Category updateCategory(String id, UpdateCategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public void deleteCategoryById(String id, boolean allowDeleteChild) {

    }
}
