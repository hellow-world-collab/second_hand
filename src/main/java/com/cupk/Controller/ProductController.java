package com.cupk.Controller;


import com.cupk.pojo.Product;
import com.cupk.Service.ProductService;
import com.cupk.utils.AliOSSUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller//表明为一个控制器
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    //查询所有商品
    @GetMapping("/productsa")
    public ModelAndView findAllProducts(@RequestParam(value="start",defaultValue = "1") int start,
                                        @RequestParam(value="size",defaultValue = "7") int size){
        PageHelper.startPage(start,size,"id asc");
        List<Product> products=productService.findAllProducts();
        PageInfo<Product> page=new PageInfo<>(products);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",page);
        mv.setViewName("Adminproductlist");
        return mv;
    }

    @GetMapping("/deleteProducta/{id}")//删除物品
    public ModelAndView deleteBook(@PathVariable("id") int id) {//执行删除物品 修改数据库
        productService.deleteProduct(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/productsa");//删除物品成功后跳转至列表
        return mv;
    }

    @RequestMapping("/searchProductsByStra")//模糊查询物品
    public ModelAndView searchProductsByStra(@RequestParam(value = "searchStr") String searchStr, Model model
    ,@RequestParam(value="start",defaultValue = "1") int start,
    @RequestParam(value="size",defaultValue = "7") int size)
    {
        PageHelper.startPage(start,size,"id asc");
        List<Product> products = productService.searchProductsByStr(searchStr);
        PageInfo<Product> page=new PageInfo<>(products);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",page);
        mv.setViewName("Adminproductlist");
        return mv;
    }

//    批量删除二手物品
    @GetMapping("/deleteProductsa")
    public ModelAndView deleteProducts(int[] id){
        productService.deleteProducts(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:productsa");//删除成功后
        return mv;
    }

    //管理员编辑
    @GetMapping("/product/{id}")//根据指定编号查找图书
    public ModelAndView findProductById(@PathVariable("id") int id) {
        Product pro = productService.findProductByid(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pro", pro);
        mv.setViewName("productdetail");
        return mv;
    }

    @PostMapping("/updateProducta")
    public ModelAndView updateProduct(Product pro,MultipartFile imges) {//执行修改图书 修改数据库
//        productService.updateProduct(pro);
        ModelAndView mv = new ModelAndView();
        try {
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                pro.setImgurl(url);
                System.out.println(pro.getImgurl());
                productService.updateProduct(pro);
                System.out.println(pro);
            }
            else{
                productService.updateProductimgnull(pro);
            }
        }
        catch (Exception e) {
            mv.setViewName("redirect:/updateProducta");
        }
        mv.setViewName("redirect:/productsa");//修改图书成功后跳转至列表
        return mv;
    }

    @GetMapping("/toupdateproducta/{id}")//跳转到修改图书页面
    public ModelAndView toUpdateProduct(@PathVariable("id") int id) {
        Product pro = productService.findProductByid(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pro", pro);
        mv.setViewName("updateproductAdmin");
        return mv;
    }


}

