package com.cupk.Service;

import com.cupk.mapper.ProductMapper;
import com.cupk.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service//引入Spring框架中定义的@Service注解。
// Spring可以自动检测和配置标记了@Service的类，而不需要你在XML配置文件中显式地声明它们。
@Primary//导入（import）语句，它用于引入Spring框架中定义的@Primary注解。
//bean被标记为@Primary，那么Spring将优先注入这个bean
public class ProductServiceImpl implements ProductService {


    @Autowired//当Spring容器创建一个bean时，它可以自动地发现并注入这个bean所依赖的其他bean。
    private ProductMapper productMapper;
    @Override
    public List<Product> searchProductsIDByStr(String searchStr, Integer id) {
        return productMapper.searchProductsIDByStr(searchStr, id);
    }

    @Override
    public Integer addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public Integer updateProductmark(Integer id) {
        return productMapper.updateProductmark(id);
    }

    @Override
    //查找全部二手物品
    public List<Product> findAllProducts() {
        return productMapper.findAllProducts();
    }

    @Override
    //删除二手物品
    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }

    //全局模糊搜索二手物品
    @Override
    public List<Product> searchProductsByStr(String searchStr) {
        return productMapper.searchProductsByStr(searchStr);
    }

    //批量删除二手物品
    @Override
    public void deleteProducts(int[] id) {
        productMapper.deleteProducts(id);
    }

    @Override
    public List<Product> findProductByids(List<Integer> ids) {
        return productMapper.findProductByids(ids);
    }

    @Override
    public Product findProductByid(Integer id) {
        return productMapper.findProductByid(id);
    }

    @Override
    public Integer updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public Integer updateProductimgnull(Product product) {
        return productMapper.updateProductimgnull(product);
    }

    @Override
    public List<Product> findProductByUid(Integer id) {
        return productMapper.findProductByUid(id);
    }
}
