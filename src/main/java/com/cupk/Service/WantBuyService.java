package com.cupk.Service;

import com.cupk.pojo.want_buy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//MyBatis框架,标记一个接口为MyBatis的Mapper接口，
// 这样MyBatis就知道这个接口中的方法应该映射到哪些SQL语句。
public interface WantBuyService {
    public void addwantbuy(want_buy wb);//添加求购
    List<want_buy> getAllwantbuy();
    List<want_buy>selectwanbuybyU(Integer userid);

}
