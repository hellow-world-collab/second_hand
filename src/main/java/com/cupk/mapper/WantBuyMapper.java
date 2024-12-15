package com.cupk.mapper;

import com.cupk.pojo.want_buy;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Mapper
public interface WantBuyMapper {
    public void addwantbuy(want_buy wb);//添加求购
    List<want_buy> getAllwantbuy();
    List<want_buy>selectwanbuybyU(Integer userid);
}
