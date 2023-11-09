package com.learning.java_web.models.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString
public class PagingResponse {
    private List<?> content;
    private long totalItems;
    private int numberOfItems;
    private int pageNumber;
    private int pageSize;
    private int totalPages;

    public PagingResponse(Page<?> page) {
        this.content = page.getContent();
        this.totalItems = page.getTotalElements();
        this.numberOfItems = page.getNumberOfElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
    }

    public PagingResponse(List<?> content, Page<?> page) {
        this.content = content;
        this.totalItems = page.getTotalElements();
        this.numberOfItems = page.getNumberOfElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
    }
}
