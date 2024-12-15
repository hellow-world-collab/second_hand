package com.cupk.Service;

import com.cupk.pojo.Index_Commodity;

import java.util.List;


public interface Index_Commodity_Service {
    List<Index_Commodity> selectCommodityByType(String commodity_Type);
    List<Index_Commodity> find_All_Commoditys();
    List<Index_Commodity> find_All_Commoditys_ByStr(String searchStr);

}
