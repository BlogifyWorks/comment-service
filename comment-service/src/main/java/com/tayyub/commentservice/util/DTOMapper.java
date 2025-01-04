package com.tayyub.commentservice.util;

import com.tayyub.commentservice.dto.CommentDTO;
import com.tayyub.commentservice.entity.Comment;

public class DTOMapper {
    public static CommentDTO toCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserId(comment.getUserId());
        dto.setBlogId(comment.getBlogId());
        dto.setParentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null);
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());
        return dto;
    }

    public static Comment toCommentEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setUserId(dto.getUserId());
        comment.setBlogId(dto.getBlogId());
        return comment;
    }
}
