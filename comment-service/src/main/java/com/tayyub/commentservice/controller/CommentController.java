package com.tayyub.commentservice.controller;


import com.tayyub.commentservice.dto.CommentDTO;
import com.tayyub.commentservice.entity.Comment;
import com.tayyub.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody Comment comment) {
        CommentDTO createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping("blog/{blogId}")
    public ResponseEntity<List<CommentDTO>>getCommentsByBlogId(@PathVariable Long blogId) {
        List<CommentDTO> comments = commentService.getCommentsByBlogId(blogId);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/parent/{parentCommentId}")
    public ResponseEntity<List<CommentDTO>> getRepliesByParentCommentId(@PathVariable Long parentCommentId) {
        List<CommentDTO> replies = commentService.getCommentsByParentCommentId(parentCommentId);
        return ResponseEntity.ok(replies);
    }

    // Get all comments
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable Long userId) {
        List<CommentDTO> comments = commentService.getAllCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }

    // Delete a comment by ID
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }


}
