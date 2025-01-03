package com.tayyub.commentservice.repository;


import com.tayyub.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogId(Long blogId);
    List<Comment> findByParentCommentId(Long parentCommentId);
    List<Comment> findByUserId(Long userId);
}
