package com.learning.java_web.services.category;

import com.learning.java_web.commons.responses.RestApiMessage;
import com.learning.java_web.commons.responses.RestApiStatus;
import com.learning.java_web.commons.validators.Validator;
import com.learning.java_web.models.entities.Category;
import com.learning.java_web.models.requests.CategoryRequest;
import com.learning.java_web.models.requests.UpdateCategoryRequest;
import com.learning.java_web.models.responses.TreeCategoryResponse;
import com.learning.java_web.repositories.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Category> getPageCategory(String searchKey, Pageable pageable) {
        return categoryRepo.getPageCategoryWithCategory("%" + searchKey + "%", pageable);
    }

    @Override
    public Category getCategoryById(String id) {
        Category category = categoryRepo.findById(id).orElse(null);
        Validator.notNull(category, RestApiStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        return category;
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        Category categoryFindByName = categoryRepo.findByName(categoryRequest.getName());
        Validator.mustNull(categoryFindByName, RestApiStatus.BAD_REQUEST, RestApiMessage.NAME_ALREADY_EXISTED);
        if (Validator.isNull(categoryFindByName)) categoryRepo.save(categoryFindByName);
        Validator.notNullAndNotEmpty(categoryRequest.getParentId(), RestApiStatus.BAD_REQUEST, RestApiMessage.PARENT_ID_INVALID);
        Category categoryFindById = categoryRepo.findById(categoryRequest.getParentId()).orElse(null);
        Validator.notNull(categoryFindById, RestApiStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        Category categoryCreated = Category.builder()
                .name(categoryRequest.getName())
                .parentId(categoryFindById.getId())
                .build();
        categoryRepo.save(categoryCreated);
    }

    @Override
    public void updateCategory(String id, UpdateCategoryRequest categoryRequest) {

    }

    @Override
    public void deleteCategoryById(String id, boolean allowDeleteChild) {

    }
}
