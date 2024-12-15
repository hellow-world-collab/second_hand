package com.cupk.Controller;

import com.cupk.Service.UserService;
import com.cupk.pojo.User;
import com.cupk.utils.AliOSSUtils;
//import com.cupk.utils.JWTUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
//    @Autowired
//    private PageHelperAutoConfiguration pageHelperAutoConfiguration;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private HttpSession httpSession;

    @GetMapping("/login")
    public ModelAndView user() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login");
        return mv;
    }
    @PostMapping("/loginstation")//首页
    public String findUser(User user,String role, Model model){//权限登录
        System.out.println(role);
        User user1;
        if (role.equals("users")) {
             user1 = userService.findByUser(user);
        }
        else{
            user1 = userService.findByAdmin(user);

        }
        System.out.println(user1);
        try {
            if(user1!=null) {

////                登录成功生成JWT令牌
//                Map<String,Object> map = new HashMap<>();
//                map.put("id",user1.getId());
//                map.put("username",user1.getUsername());
//                map.put("password",user1.getPassword());
//                map.put("phone",user1.getPhone());
//                map.put("email",user1.getEmail());
//                map.put("role",role);
//                map.put("imgurl",user1.getImgurl());
//                String jwt =JWTUtils.GenJWT(map); //包含员工信息
                httpSession.setAttribute("loginUser", user1);//将登录的用户放到session中

                System.out.println(httpSession.getId());
                System.out.println("________________________________________________");
                if(role.equals("users")){
//                    model.addAttribute("msg", user1);//把信息传到用户端首页
//                    return "public/success";
                    return "redirect:/index";//像这样到我的商品
                }
                else{
//                    model.addAttribute("msg", "Admin信息查询成功！");//管理员首页
                    return "redirect:/htgl";

                }
            }
            else{
                throw new RuntimeException("账户或用户名密码错误");
            }
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }
    @GetMapping("/htgl")//跳转到管理首页
    public ModelAndView begin() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("ManagementSystem");
        return mv;
    }
    @GetMapping("/myproducts")//跳转到管理首页
    public String beginp() {
        return "redirect:/products";
    }
    @GetMapping("/Myposts")//跳转到管理首页
    public String beginposts() {

        return "redirect:/userposts";
    }


//
//    @PostMapping("/addUser")
//    public void upload(User user, MultipartFile imgurl) throws Exception {
//        String originalFilename = imgurl.getOriginalFilename();
//        int index = originalFilename.lastIndexOf(".");
//        String suffix = originalFilename.substring(index);
//        String newfilename= UUID.randomUUID().toString()+suffix;
//        imgurl.transferTo(new File("D:\\Users\\WJF\\IdeaProjects\\ems-thymeleaf\\src\\main\\resources\\static\\fileByuser"+newfilename));

//    }
//    重写添加员工
//    @GetMapping("/addUser")
//    public String addUser(){
//        return "addUser";
//    }
//    @PostMapping("/addUser")//注册
//    public ModelAndView addUser(User user){//执行添加，修改数据库
//        ModelAndView mv = new ModelAndView();
//        try {
////            log.info("文件上传，文件名：{}", imgurl.getOriginalFilename());
//        //调用阿里云OSS
////        String url=aliOSSUtils.upload(imgurl);
////        log.info("文件上传完成，文件访问的url:{}",url);
//            userService.addUser(user);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            mv.setViewName("redirect:/addUser");
//            return mv;
//        }
//        mv.setViewName("redirect:/login");//添加完后再次回到登录界面
//        return mv;
//    }
//    @PostMapping("/adduser")
//    public void save(@RequestBody User user){
//        log.info("新增用户,user:{}",user);
//        userService.addUser(user);
//    }

//    @GetMapping("/addUserByAdmin")
//    public String addUserByAdmin(){
//        return "addUserByAdmin";
//    }
//@PostMapping("/addUserByAdmin")//注册
//public ModelAndView addUserByAdmin(User user){//执行添加，修改数据库
//    ModelAndView mv = new ModelAndView();
//    try {
//        userService.addUser(user);
//    }
//    catch (Exception e) {
//        e.printStackTrace();
//        mv.setViewName("redirect:/addUserByAdmin");
//        return mv;
//    }
//    mv.setViewName("redirect:/users");
//    return mv;
//}


    @GetMapping("/users")
    public ModelAndView findAllUsers(@RequestParam(value="start",defaultValue = "1")int start,
                                     @RequestParam(value="size",defaultValue = "5")int size ){
        PageHelper.startPage(start,size,"id asc");//根据什么序列进行分页
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.findAllUsers();
        PageInfo<User> page=new PageInfo<>(users);
        System.out.println(users.size());
        mv.addObject("page", page);
        mv.setViewName("Userlist");
        return mv;
    }
    @GetMapping("/deleteUsers")
    public ModelAndView deleteUsers(int[] id){//批量删除图书的方法
        userService.deleteUsers(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:/users");//返回图书列表
        return mv;
    }
    //一点问题
    @RequestMapping("/searchUsersByStr")//查询部分图书,这个value=searchStr和前端一致
    public String searchBooksByStr(@RequestParam(value = "searchStr") String searchStr, Model model){
//        PageHelper.startPage(start,size,"id asc");//根据什么序列进行分页
        if(!searchStr.equals("")){
            List<User> users=userService.searchUsersByStr(searchStr);
            PageInfo<User> page=new PageInfo<>(users);
            model.addAttribute("page",page);
            return "Userlist";//一个页面的地址，返回到Userlist
        }
        else{
            return "redirect:/users";
        }
    }

    @RequestMapping("/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable Integer id){
            userService.deleteUser(id);
            ModelAndView mv=new ModelAndView();
            mv.setViewName("redirect:/users");
            return mv;
    }
    @RequestMapping("/toupdateUser/{id}")
    public ModelAndView toupdateUser(@PathVariable Integer id){
        ModelAndView mv=new ModelAndView();
        User user= userService.findUserByid(id);
        mv.addObject("user",user);
        mv.setViewName("updateUser");
        return mv;
    }
    @RequestMapping("/updateUser")
    public ModelAndView updateUser(User user,MultipartFile imges) throws Exception{
        ModelAndView mv=new ModelAndView();
        //调用阿里云OSS
        try {
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                user.setImgurl(url);
            }
            userService.updateUser(user);
        }
        catch (Exception e) {
            mv.setViewName("redirect:/addUserByAdmin");
        }
        mv.setViewName("redirect:/users");
        return mv;
    }

    @RequestMapping("/upgrade/{id}")
    public ModelAndView upgrade(@PathVariable Integer id){
        ModelAndView mv=new ModelAndView();
        User user=userService.findUserByid(id);
        if(userService.findByAdmin(user)==null){
            userService.addUsertoAdmin(user);
        }
        mv.setViewName("redirect:/users");
        return mv;
    }
}
