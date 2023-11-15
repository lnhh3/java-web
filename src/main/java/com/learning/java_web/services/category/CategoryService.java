package com.learning.java_web.services.category;

import com.learning.java_web.commons.enums.SystemStatus;
import com.learning.java_web.commons.responses.RestApiMessage;
import com.learning.java_web.commons.responses.RestApiStatus;
import com.learning.java_web.commons.validators.Validator;
import com.learning.java_web.helper.CategoryHelper;
import com.learning.java_web.models.entities.Category;
import com.learning.java_web.models.requests.CategoryRequest;
import com.learning.java_web.models.requests.UpdateCategoryRequest;
import com.learning.java_web.models.responses.PagingResponse;
import com.learning.java_web.models.responses.TreeCategoryResponse;
import com.learning.java_web.repositories.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepo categoryRepo;
    @Autowired
    CategoryHelper categoryHelper;

    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public List<TreeCategoryResponse> getTreeCategories() {
        List<Category> categoryList = this.categoryRepo.findAllOrderLevel();
        TreeCategoryResponse root = new TreeCategoryResponse();
        for (Category category : categoryList) {
            if (category.getParentId() == null) {
                root.getChildren().add(categoryHelper.convertCategoryToTreeItem(category));
            } else {
                categoryHelper.addNodeToTree(root, category);
            }
        }
        return root.getChildren();
    }

    @Override
    public TreeCategoryResponse getTreeCategoriesById(String id) {
        Category category = this.getCategoryById(id);
        Validator.notNull(category, RestApiStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        TreeCategoryResponse root = new TreeCategoryResponse(category);
        List<Category> categoryChildren = this.categoryRepo.findAllByLevelGreaterThanOrderByLevel(category.getLevel());
        for (Category categoryChild : categoryChildren) {
            categoryHelper.addNodeToTree(root, categoryChild);
        }
        return root;
    }

    @Override
    public List<Category> getCategoriesByParentId(String parentId) {
        Category category = categoryRepo.findById(parentId).orElse(null);
        Validator.notNull(category, RestApiStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        return categoryRepo.findAllByParentId(parentId);
    }

    @Override
    public PagingResponse getPageCategory(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortType, "name"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        PagingResponse pagingResponse = new PagingResponse((categoryRepo.getPageCategoryWithCategory("%" + searchKey + "%", pageable)));
        return pagingResponse;
    }

    @Override
    public Category getCategoryById(String id) {
        Category category = categoryRepo.findById(id).orElse(null);
        Validator.notNull(category, RestApiStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        return category;
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        Validator.notNullAndNotEmpty(categoryRequest.getName(), RestApiStatus.BAD_REQUEST, RestApiMessage.CATEGORY_NAME_INVALID);
        Category categoryFindByName = categoryRepo.findByName(categoryRequest.getName());
        Validator.mustNull(categoryFindByName, RestApiStatus.EXISTED, RestApiMessage.NAME_ALREADY_EXISTED);

        Category categoryFindById = null;
        if (categoryRequest.getParentId() != null) {
            Validator.notEmpty(categoryRequest.getParentId(), RestApiStatus.BAD_REQUEST_PARAM, RestApiMessage.PARENT_ID_CANNOT_EMPTY);
            categoryFindById = getCategoryById(categoryRequest.getParentId());
        }

        boolean isParentIdNull = categoryFindById == null;
        String categoryParentId = isParentIdNull ? null : categoryFindById.getId();
        int level = isParentIdNull ? 0 : categoryFindById.getLevel() + 1;

        Category categoryCreated = Category.builder()
                .name(categoryRequest.getName())
                .level(level)
                .parentId(categoryParentId)
                .status(SystemStatus.ACTIVE)
                .build();

        categoryRepo.save(categoryCreated);
    }

    @Override
    public void updateCategory(String id, UpdateCategoryRequest categoryRequest) {
        Category categoryFindById = categoryRepo.findById(id).orElse(null);
        Validator.notNull(categoryFindById, RestApiStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);
        Category categoryFindByName = categoryRepo.findByName(categoryRequest.getName());
        Validator.mustNull(categoryFindByName, RestApiStatus.EXISTED, RestApiMessage.NAME_ALREADY_EXISTED);

        categoryFindById.setName(categoryRequest.getName());

        categoryRepo.save(categoryFindById);
    }

    @Override
    public void deleteCategoryById(String id, boolean allowDeleteChild) {
        Category categoryFindById = categoryRepo.findById(id).orElse(null);
        Validator.notNull(categoryFindById, RestApiStatus.NOT_FOUND, RestApiMessage.CATEGORY_NOT_FOUND);

        if (allowDeleteChild) {
            List<Category> categoryChildren = categoryRepo.findAllByParentId(id);
            List<Category> deleteCategories = new ArrayList<>();
            deleteCategoriesWithChild(categoryChildren, deleteCategories);
            categoryRepo.deleteAll(deleteCategories);
        } else {
            List<Category> categories = getCategoriesByParentId(categoryFindById.getId());
            Validator.mustEqual(categories.size(), 0, RestApiStatus.CONFLICT, RestApiMessage.CANNOT_DELETED_WHILE_CHILDREN_EXISTED);
        }

        categoryRepo.deleteById(categoryFindById.getId());
    }

    private void deleteCategoriesWithChild(List<Category> categories, List<Category> deleteCategories) {
        deleteCategories.addAll(categories);

        for (Category c : categories) {
            String parentId = c.getId();
            List<Category> categoriesFindByParentId = categoryRepo.findAllByParentId(parentId);
            deleteCategoriesWithChild(categoriesFindByParentId, deleteCategories);
        }
    }
}
