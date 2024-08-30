package com.learnings.notes_app_graphql.repository;

import com.learnings.notes_app_graphql.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<List<Category>> findAllByUserId(UUID userId);
}
