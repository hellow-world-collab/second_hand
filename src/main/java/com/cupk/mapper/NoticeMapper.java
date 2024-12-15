package com.cupk.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    public String SelectNotice();
    public Integer UpdateNotice(String notice);
}
