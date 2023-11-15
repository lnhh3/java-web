package com.learning.java_web.models.responses;

import com.learning.java_web.models.entities.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class TreeCategoryResponse {
    private String id;
    private String name;
    private int level;
    private List<TreeCategoryResponse> children;

    public TreeCategoryResponse() {
        this.id = "--root--";
        this.name = "root";
        this.level = -1;
        this.children = new ArrayList<>();
    }

    public TreeCategoryResponse(Category category) {
        this.id = category.getId();
        this.level = category.getLevel();
        this.name = category.getName();
        this.children = new ArrayList<>();
    }
}
