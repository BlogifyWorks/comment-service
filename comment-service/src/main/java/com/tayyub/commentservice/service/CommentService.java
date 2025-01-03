package com.tayyub.commentservice.service;

import com.tayyub.commentservice.entity.Comment;
import com.tayyub.commentservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment ){
        if (comment.getParentComment() != null && comment.getParentComment().getId() != null) {
            Comment parentComment = commentRepository.findById(comment.getParentComment().getId())
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
            comment.setParentComment(parentComment);
        }
        return commentRepository.save(comment);
    }
    public List<Comment> getCommentsByBlogId(Long blogId){
        return commentRepository.findByBlogId(blogId);
    }

    public List<Comment> getCommentsByParentCommentId(Long parentCommentId) {
        return commentRepository.findByParentCommentId(parentCommentId);
    }

    // Fetch all comments (optional)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Delete a comment by its ID
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> getAllCommentsByUser(Long userId){
        return commentRepository.findByUserId(userId);
    }
}
