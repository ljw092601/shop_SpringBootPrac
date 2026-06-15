package com.example.shop.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findByParentId(Integer parentId, Pageable pageable);
    long countByParentId(Integer parentId);
}
