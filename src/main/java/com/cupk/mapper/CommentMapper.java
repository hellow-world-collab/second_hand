package com.cupk.mapper;

import com.cupk.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(Comment comment);
    List<Comment> findCommentsByCircleId(int circleId);
}