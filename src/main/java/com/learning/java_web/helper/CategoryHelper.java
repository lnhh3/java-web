package com.learning.java_web.helper;

import com.learning.java_web.models.entities.Category;
import com.learning.java_web.models.responses.TreeCategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CategoryHelper {
    public TreeCategoryResponse convertCategoryToTreeItem(Category category) {
        return TreeCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .level(category.getLevel())
                .children(new ArrayList<>())
                .build();
    }

    public void addNodeToTree(TreeCategoryResponse root, Category category) {
        int parentLevel = category.getLevel() - 1;
        if(root.getLevel() == parentLevel && root.getId().equals(category.getParentId())) {
            root.getChildren().add(new TreeCategoryResponse(category));
            return;
        }

        for (TreeCategoryResponse treeCategoryResponse: root.getChildren()) {
            if(treeCategoryResponse.getLevel() != parentLevel) {
                addNodeToTree(treeCategoryResponse, category);
            } else if(treeCategoryResponse.getId().equals(category.getParentId())) {
                treeCategoryResponse.getChildren().add(this.convertCategoryToTreeItem(category));
            }
        }
    }
}
