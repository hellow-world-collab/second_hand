package com.cupk.Service;


import com.cupk.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public String SelectNotice() {
        return noticeMapper.SelectNotice();
    }

    @Override
    public Integer UpdateNotice(String notice) {
        return noticeMapper.UpdateNotice(notice);
    }
}
