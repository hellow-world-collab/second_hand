package com.cupk.Service;

import com.cupk.mapper.CircleMapper;
import com.cupk.mapper.CommentMapper;
import com.cupk.pojo.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleServiceImpl implements CircleService {
    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Circle> findAllCircles() {
        List<Circle> circles = circleMapper.findAllCircles();
        for (Circle circle : circles) {
            circle.setComments(commentMapper.findCommentsByCircleId(circle.getId()));
        }
        return circles;
    }
}