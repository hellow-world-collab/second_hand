package com.cupk.Controller;

import com.cupk.Service.ProductService;
import com.cupk.pojo.Product;
import com.cupk.pojo.User;
import com.cupk.utils.AliOSSUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class myProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    private User user1=new User();

    //查询所有商品
//    @RequestParam(value="start",defaultValue = "1") int start,
//                                        @RequestParam(value="size",defaultValue = "4") int size,
    @GetMapping("/products")
    public ModelAndView findAllProducts(HttpServletRequest request){
//        PageHelper.startPage(start,size,"id asc");
        HttpSession session=request.getSession();
        user1=(User)session.getAttribute("loginUser");
        List<Product> products=productService.findProductByUid(user1.getId());
//        List<Product> products=productService.findAllProducts();

//        PageInfo<Product> page=new PageInfo<>(products);
        ModelAndView mv = new ModelAndView();
//        mv.addObject("page",page);
        mv.addObject("page",products);
        mv.setViewName("productlist");
        return mv;
    }

    @GetMapping("/deleteProduct/{id}")//删除物品
    public ModelAndView deleteBook(@PathVariable("id") int id) {//执行删除物品 修改数据库
        productService.deleteProduct(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/products");//删除物品成功后跳转至列表
        return mv;
    }

    @RequestMapping("/searchProductsByStr")//模糊查询物品
    public ModelAndView searchProductsByStr(@RequestParam(value = "searchStr") String searchStr, Model model)
    {
//        PageHelper.startPage(start,size,"id asc");
        List<Product> products = productService.searchProductsIDByStr(searchStr,user1.getId());
//        PageInfo<Product> page=new PageInfo<>(products);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",products);
        mv.setViewName("productlist");
        return mv;
    }

    //批量删除二手物品
//    @RequestMapping("/deleteProducts")
//    public ModelAndView UserdeleteProducts(@RequestParam int[] id){
////        System.out.println(id);
////        String[] numberArray = id.split(",");
////        Integer[] integerArray = new Integer[numberArray.length];
////        for (int i = 0; i < numberArray.length; i++) {
////            integerArray[i] = Integer.parseInt(numberArray[i]);
////        }
//        log.info("deleteProducts id:{}",id);
//        productService.deleteProducts(id);
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("redirect:/products/"+user1.getId());//删除成功后
//        return mv;
//    }
    @GetMapping("/deleteProducts")
    public ModelAndView UserdeleteProducts(int[] id){//批量删除图书的方法
//        List idList= Arrays.asList(id.split(","));
//        int [] ids=new int[idList.size()];
//        for(int i=0;i<idList.size();i++){
//            ids[i]=Integer.parseInt(idList.get(i).toString());
//        }
//        log.info("要删除的商品id序列 ,{}",ids);
        productService.deleteProducts(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:/products");//返回图书列表
        return mv;
    }
    //编辑
    @RequestMapping("/toupdateproduct/{id}")
    public ModelAndView toupdateProduct(@PathVariable Integer id){
        ModelAndView mv=new ModelAndView();
        Product product= productService.findProductByid(id);
        mv.addObject("product",product);
        mv.setViewName("updateProduct");
        return mv;
    }
    @RequestMapping("/updateProduct")
    public ModelAndView updateUser(Product product,MultipartFile imges) throws Exception{
        ModelAndView mv=new ModelAndView();
        //调用阿里云OSS
        try {
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                product.setImgurl(url);
                System.out.println(product.getImgurl());
                productService.updateProduct(product);
                System.out.println(product);
            }
            else{
                productService.updateProductimgnull(product);
            }
        }
        catch (Exception e) {
            mv.setViewName("redirect:/updateProduct");
        }
        mv.setViewName("redirect:/products");
        return mv;
    }
    @RequestMapping("/addProductByU")
    public String addProductByUser(){
        return "addProductByUser";
    }
    @RequestMapping("/addProductByUser")
    public ModelAndView addProduct(Product product,MultipartFile imges) throws Exception{
        ModelAndView mv=new ModelAndView();
        try {
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                product.setImgurl(url);
                System.out.println(product.getImgurl());
            }
                //加入
                productService.addProduct(product);
                System.out.println(product);

        }
        catch (Exception e) {
            mv.setViewName("redirect:/addProductByUser");
        }
        mv.setViewName("redirect:/products");
        return mv;
    }
}
