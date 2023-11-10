package com.learning.java_web.controllers;

import com.learning.java_web.models.requests.CategoryRequest;
import com.learning.java_web.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractBaseController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity getCategories() {
        return responseUtil.successResponse(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoryById(@PathVariable("id") String id) {
        categoryService.getCategoryById(id);
        return responseUtil.successResponse(null);
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return responseUtil.successResponse("ok");
    }
}
