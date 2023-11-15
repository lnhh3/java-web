package com.learning.java_web.controllers;

import com.learning.java_web.models.requests.CategoryRequest;
import com.learning.java_web.models.requests.UpdateCategoryRequest;
import com.learning.java_web.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractBaseController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/page")
    public ResponseEntity getPageCategory(@RequestParam(name = "page_number", defaultValue = "1", required = false) int pageNumber,
                                          @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
                                          @RequestParam(name = "sort_type", defaultValue = "ASC", required = false) Sort.Direction sortType,
                                          @RequestParam(name = "search_key", defaultValue = "", required = false) String searchKey) {
        return responseUtil.successResponse(categoryService.getPageCategory(pageNumber, pageSize, sortType, searchKey));
    }

    @GetMapping("/tree")
    public ResponseEntity getTreeCategories() {
        return responseUtil.successResponse(categoryService.getTreeCategories());
    }

    @GetMapping("/tree/{id}")
    public ResponseEntity getTreeCategoriesById(@PathVariable("id") String id) {
        return responseUtil.successResponse(categoryService.getTreeCategoriesById(id));
    }

    @GetMapping("parent/{parent_id}")
    public ResponseEntity getCategoriesByParentId(@PathVariable("parent_id") String parentId) {
        return responseUtil.successResponse(categoryService.getCategoriesByParentId(parentId));
    }

    @GetMapping
    public ResponseEntity getCategories() {
        return responseUtil.successResponse(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable("id") String id) {
        return responseUtil.successResponse(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return responseUtil.successResponse("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable("id") String id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        categoryService.updateCategory(id, updateCategoryRequest);
        return responseUtil.successResponse("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") String id,
                                         @RequestParam(name = "allow_delete_children", defaultValue = "false", required = false)
                                         boolean allowDeleteChildren)
    {
        categoryService.deleteCategoryById(id, allowDeleteChildren);
        return responseUtil.successResponse("ok");
    }
}
