package com.learning.java_web.services.category;

import com.learning.java_web.models.entities.Category;
import com.learning.java_web.models.requests.CategoryRequest;
import com.learning.java_web.models.requests.UpdateCategoryRequest;
import com.learning.java_web.models.responses.TreeCategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategories();

    List<TreeCategoryResponse> getTreeCategories();

    List<TreeCategoryResponse> getTreeCategoriesById(String id);

    List<Category> getCategoriesByParentId(String parentId);

    Category getCategoryById(String id);

    void createCategory(CategoryRequest categoryRequest);

    void updateCategory(String id, UpdateCategoryRequest categoryRequest);

    void deleteCategoryById(String id, boolean allowDeleteChild);
}
