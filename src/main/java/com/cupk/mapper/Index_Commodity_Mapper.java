package com.cupk.mapper;

import com.cupk.pojo.Index_Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Index_Commodity_Mapper {
    List<Index_Commodity> selectCommodityByType(String commodity_Type);
    List<Index_Commodity> find_All_Commoditys();
    List<Index_Commodity> find_All_Commoditys_ByStr(String searchStr);
}
