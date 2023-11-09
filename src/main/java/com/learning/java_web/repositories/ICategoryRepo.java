package com.learning.java_web.repositories;

import com.learning.java_web.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepo extends JpaRepository<Category, String> {
    Category findByName(String name);
}
