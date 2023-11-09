package com.learning.java_web.models.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreeCategoryResponse {
    private String id;
    private String name;
    private List<TreeCategoryResponse> children;
}
