package com.cupk.Service;

import com.cupk.mapper.WantBuyMapper;
import com.cupk.pojo.want_buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//引入Spring框架中定义的@Service注解。
// Spring可以自动检测和配置标记了@Service的类，而不需要你在XML配置文件中显式地声明它们。
@Primary//导入（import）语句，它用于引入Spring框架中定义的@Primary注解。
//bean被标记为@Primary，那么Spring将优先注入这个bean
public class WantBuyServiceImpl implements WantBuyService {
    @Autowired
    public WantBuyMapper wantBuyMapper;

    @Override
    public void addwantbuy(want_buy wb) {
        wantBuyMapper.addwantbuy(wb);
    }

    @Override
    public List<want_buy> getAllwantbuy() {
        return wantBuyMapper.getAllwantbuy();
    }

    @Override
    public List<want_buy> selectwanbuybyU(Integer userid) {
        return wantBuyMapper.selectwanbuybyU(userid);
    }
}
