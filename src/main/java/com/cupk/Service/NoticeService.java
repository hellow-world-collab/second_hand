package com.cupk.Service;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeService {
    public String SelectNotice();
    public Integer UpdateNotice(String notice);

}
