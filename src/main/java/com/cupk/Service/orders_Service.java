package com.cupk.Service;

import com.cupk.mapper.orders_Mapper;
import com.cupk.pojo.CommodityDetail;
import com.cupk.pojo.My_Sale_orders;
import com.cupk.pojo.orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface orders_Service {
    List<orders> select_Myorders(int user_id);//我买的家
    List<My_Sale_orders> select_saleorders(int user_id);//我卖的
    CommodityDetail select_commoditydetail(int commodity_id);//根据商品id进入到详细页
    public Integer insert(@Param("number")String number, @Param("userid")Integer userid, @Param("saleid")Integer saleid, @Param("useraddressid")Integer useraddressid,@Param("falge")String falge);
    public Integer selectidBynumber(String number);//查找订单id
    public Integer  insertordersitem(@Param("order_id")Integer order_id,@Param("product_id")Integer product_id);
    public Integer updateordersitem(Integer order_id);//更新ordersitem状态为已支付
    public void deleteorder(Integer id);//删除订单
    List<orders> select_Myorders_rental(int user_id);
    List<My_Sale_orders> select_saleorders_rental(int user_id);

}
