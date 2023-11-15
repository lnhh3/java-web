package com.learning.java_web.repositories;

import com.learning.java_web.models.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepo extends JpaRepository<Category, String> {
    @Query("""
    select c from Category c where c.name like :search_key
    """)
    Page<Category> getPageCategoryWithCategory(@Param("search_key") String searchKey, Pageable pageable);

    Category findByName(String name);

    List<Category> findAllByParentId(String parentId);

    @Query("""
        select c from Category c order by c.level
    """)
    List<Category> findAllOrderLevel();

    List<Category> findAllByLevelGreaterThanOrderByLevel(int level);
}
