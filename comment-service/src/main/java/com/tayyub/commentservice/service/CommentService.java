package com.tayyub.commentservice.service;

import com.tayyub.commentservice.dto.CommentDTO;
import com.tayyub.commentservice.entity.Comment;
import com.tayyub.commentservice.repository.CommentRepository;
import com.tayyub.commentservice.util.DTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDTO createComment(Comment comment){
      //  Comment comment = DTOMapper.toCommentEntity(commentDTO);
        if (comment.getParentComment() != null && comment.getParentComment().getId() != null) {
            Comment parentComment = commentRepository.findById(comment.getParentComment().getId())
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
            comment.setParentComment(parentComment);
        }
        return DTOMapper.toCommentDTO(commentRepository.save(comment));
    }

    public List<CommentDTO> getCommentsByBlogId(Long blogId){
        return commentRepository.findByBlogId(blogId).stream().map(DTOMapper::toCommentDTO).collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentsByParentCommentId(Long parentCommentId) {
        return commentRepository.findByParentCommentId(parentCommentId).stream().map(DTOMapper::toCommentDTO).collect(Collectors.toList());
    }

    // Fetch all comments (optional)
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream().map(DTOMapper::toCommentDTO).collect(Collectors.toList());
    }

    // Delete a comment by its ID
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentDTO> getAllCommentsByUser(Long userId){
        return commentRepository.findByUserId(userId).stream().map(DTOMapper::toCommentDTO).collect(Collectors.toList());
    }
}
