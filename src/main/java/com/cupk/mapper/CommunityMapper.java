package com.cupk.mapper;

import com.cupk.pojo.Community;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    List<Community> findAllCommunities(); // 查询全部社区动态
    Community findCommunityById(Integer id);
    List<Community> findCommunityByType(String type);
}
