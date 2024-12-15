package com.cupk.Service;

import com.cupk.mapper.CommentMapper;
import com.cupk.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> findCommentsByCircleId(int circleId) {
        return commentMapper.findCommentsByCircleId(circleId);
    }
}