package com.cupk.Service;

import com.cupk.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductService {
    List<Product> findAllProducts();//查询全部二手物品
    public void deleteProduct(Integer id);//删除二手物品
    public List<Product>searchProductsByStr(String searchStr);//全局模糊查询二手物品
    public void deleteProducts(int[] id);//批量删除二手物品
    public Product findProductByid(Integer id);//编辑商品的查找
    public Integer updateProduct(Product product);
    public Integer updateProductimgnull(Product product);
    public List<Product> findProductByUid(Integer id);
    public List<Product>searchProductsIDByStr(String searchStr,Integer id);//全局模糊查询二手物品
    public Integer addProduct(Product product);//用户我的商品添加功能

    public Integer updateProductmark(Integer id);//更新产品上架状态为下架

}
