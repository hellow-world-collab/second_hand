package com.cupk.Service;

import com.cupk.pojo.Comment;

import java.util.List;

public interface CommentService {
    void insertComment(Comment comment);
    List<Comment> findCommentsByCircleId(int circleId);
}