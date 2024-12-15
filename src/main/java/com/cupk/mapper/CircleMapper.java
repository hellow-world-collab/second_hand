package com.cupk.mapper;

import com.cupk.pojo.Circle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CircleMapper {
    List<Circle> findAllCircles();
}