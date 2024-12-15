package com.cupk.Service;

import com.cupk.pojo.Community;

import java.util.List;

public interface CommunityService {
    List<Community> findAllCommunities(); // 查询全部社区动态
    Community findCommunityById(Integer id);
    List<Community> findCommunityByType(String type);}
