package com.cupk.Controller;

import com.cupk.Service.*;

import com.cupk.pojo.Product;
import com.cupk.pojo.User;
import com.cupk.pojo.orders;
//import jakarta.annotation.Resource;
import com.cupk.pojo.rental_item;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class PayController {
    @Autowired
    private orders_Service orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private RentalItemService rentalItemService;

    private orders order = new orders();


    @RequestMapping("/payon")
    @ResponseBody
    public ModelAndView payon(String Useraddress, String phonenumber){
        //根据订单id获取订单
        System.out.println(order);
        System.out.println(Useraddress);
//        System.out.println(user);
//        order.setBuy_user(user);
        String number=order.getOrder_number();
        Integer uid=order.getBuy_user().getId();
        System.out.println(uid);
        Integer saleid=order.getSale_user().getId();
        //erro   改以下
//        Integer saleaddressid=userService.findidByaddres(order.getProduct().getAddress(),saleid);
        Integer useraddressid=userService.findidByaddres(Useraddress,uid);
        System.out.println(useraddressid);

//        System.out.println(saleaddressid);
        System.out.println("=============================================================================");
        //存储订单 orders,ordersitem
//        System.out.println(order.getFalge());
        orderService.insert(order.getOrder_number(),uid,saleid,useraddressid,order.getFalge());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        Integer order_id=orderService.selectidBynumber(number);
        Integer product_id=order.getProduct().getId();
//        System.out.println(s);
        orderService.insertordersitem(order_id,product_id);
        System.out.println("____________________________________________");
        //把订单加进去后状态没“未支付”，支付成功后更改
        //insert into orders(number,userid,saleid,useraddress,saleaddressid) values(order.number,order.user.id,order.sale.id,)
        //insert into ordersitem(order_id,product.id) values(order.id,order.product.id)
//        String body = new AlipayConfig().goAlipay(order);//调用支付
//        System.out.println(body);
//        AliPay aliPay = new AliPay();
//        aliPay.setSubject(order.getProduct().getName());
//        aliPay.setTotalAmount(order.getProduct().getPrice());
//        aliPay.setTraceNo(order.getOrder_number());
//        aliPay.setProductid(product_id);
//        aliPay.setOrderid(order_id);

        System.out.println("========================================================================================");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/alipay/payai?subject=" + order.getProduct().getName() +"&traceNo=" + order.getOrder_number() + "&totalAmount=" + order.getOrder_price()+"&orderid="+order_id+"&productid="+product_id+"&falge="+order.getFalge());
        return mv;
    }
//    @RequestMapping("/payDone")
//    public String payDone(@RequestParam Map<String,String> ms) throws Exception {
//        //验签
//        Boolean f = Factory.Payment.Common().verifyNotify(ms);
//        if(!f){
//            throw new BusinessException(JsonResponseStatus.UN_KNOWN);
//        }
//        String oid = ms.get("out_trade_no");
//        //调用支付宝的支付功能
//        orderService.update(new UpdateWrapper<Order>()
//                .eq("oid",oid).set("status",1).set("pay_date",new Date()));
//        return "ok";
//    }
    @RequestMapping("/pay/{id}")//根据商品id生成订单+64
    public ModelAndView pay(@PathVariable Integer id,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        User buy = (User)session.getAttribute("loginUser");//用这种方法在后端找到session中的信息
        System.out.println(buy);
//        System.out.println(session.);
        order.setFalge("0");
        order.setBuy_user(buy);
        Product product =productService.findProductByid(id);
        System.out.println(product);
        String ordernumber= UUID.randomUUID().toString().replace("-","")+ String.valueOf(id);
        System.out.println(ordernumber);
        order.setOrder_number(ordernumber);
        order.setOrder_price(product.getPrice());
        order.setProduct(product);
        User sale=userService.findUserByid(product.getUser_id());
        order.setSale_user(sale);
        System.out.println(order);
        mav.addObject("order",order);//把order传过去
        // 假设您有一个包含选项的Lis
        System.out.println("===================================================");
        List<String> address=userService.findAdressByUid(buy.getId());
        System.out.println(address);
//        List<String> optionList = Arrays.asList("Option 1", "Option 2", "Option 3");
//        for (String option : address) {
//            options.add(new SelectOption(option, option));
//        }
        mav.addObject("options",address);//把options传过去
        mav.setViewName("pay");
        return mav;
        //自动生成订单单号
        //买家就是session里面的
        //卖家就是product里面的
    }
    @RequestMapping("/payrental")//根据商品id生成订单
    @ResponseBody
    public ModelAndView pay(Integer id,Integer date,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        User buy = (User)session.getAttribute("loginUser");//用这种方法在后端找到session中的信息
        System.out.println(buy);
//        System.out.println(session.);
        order.setFalge("1");
        order.setBuy_user(buy);
        rental_item rental=rentalItemService.findRentalItemById(id);
        Double mon=rental.getPrice_one_day()*date+rental.getDeposit();
        System.out.println(mon);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        order.setOrder_price(mon);
        Product product=new Product();
        product.setName(rental.getName());
        product.setId(rental.getId());
        product.setUser_id(rental.getUser_id());
        product.setPrice(mon);
        product.setType(rental.getType());
        product.setImgurl(rental.getImgurl());
        product.setAddress(rental.getAddress());
        System.out.println(product);

        String ordernumber= UUID.randomUUID().toString().replace("-","")+ String.valueOf(id);
        System.out.println(ordernumber);
        order.setOrder_number(ordernumber);

        order.setProduct(product);
        User sale=userService.findUserByid(product.getUser_id());
        order.setSale_user(sale);
        System.out.println(order);
        mav.addObject("order",order);//把order传过去
        // 假设您有一个包含选项的Lis
        System.out.println("===================================================");
        List<String> address=userService.findAdressByUid(buy.getId());
        System.out.println(address);
//        List<String> optionList = Arrays.asList("Option 1", "Option 2", "Option 3");
//        for (String option : address) {
//            options.add(new SelectOption(option, option));
//        }
        mav.addObject("options",address);//把options传过去
        mav.setViewName("pay");
        return mav;
        //自动生成订单单号
        //买家就是session里面的
        //卖家就是product里面的
    }
    @GetMapping("/addaddressByU")
    public String addaddressBU(){
        return "addaddressByU";
    }

    @PostMapping("/addAddress")
    public ModelAndView addAddress(Integer userid,String address,String username,String userphone) {//执行添加地址 修改数据库
        System.out.println("__________________________________________________________");
        System.out.println(userid);
        System.out.println(address);
        System.out.println(username);
        System.out.println(userphone);
        addressService.addAddressByU(address,userid,username,userphone);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/pay/"+order.getProduct().getId());//添加地址成功后跳转至列表
        return mv;
    }
}
