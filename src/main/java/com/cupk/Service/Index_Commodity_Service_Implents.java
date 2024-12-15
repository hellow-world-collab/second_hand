package com.cupk.Service;

import com.cupk.mapper.Index_Commodity_Mapper;
import com.cupk.pojo.Index_Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class Index_Commodity_Service_Implents implements Index_Commodity_Service {
    @Autowired
    private Index_Commodity_Mapper index_commodity_mapper;
    @Override
    public List<Index_Commodity> selectCommodityByType(String commodity_Type) {
        return index_commodity_mapper.selectCommodityByType(commodity_Type);
    }

    @Override
    public List<Index_Commodity> find_All_Commoditys() {
        return index_commodity_mapper.find_All_Commoditys();
    }

    @Override
    public List<Index_Commodity> find_All_Commoditys_ByStr(String searchStr) {
        return index_commodity_mapper.find_All_Commoditys_ByStr(searchStr);
    }
}
