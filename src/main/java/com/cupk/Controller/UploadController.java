package com.cupk.Controller;

import com.cupk.Service.UserService;
import com.cupk.pojo.Result;
import com.cupk.pojo.User;
import com.cupk.utils.AliOSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class UploadController {
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private UserService userService;
    @GetMapping("/upload")
    public String upload() {
        return "addUser";
    }
    @PostMapping("/upload")
    public ModelAndView Upload(User user, MultipartFile imges) throws Exception {

        ModelAndView mv = new ModelAndView();
        try {//调用阿里云OSS
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                user.setImgurl(url);
            }
            userService.addUser(user);
        }
        catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("redirect:/addUser");
        }
        mv.setViewName("redirect:/login");//添加完后再次回到登录界面
        return mv;
    }
    @GetMapping("/addUserByAdmin")
    public String uploadByAdmin() {
        return "addUserByAdmin";
    }
    @PostMapping("/addUserByAdmin")
    public ModelAndView UploadByAdmin(User user,String role, MultipartFile imges) throws Exception {

        ModelAndView mv = new ModelAndView();
        try {//调用阿里云OSS
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                user.setImgurl(url);
            }
            if(role.equals("users")) userService.addUser(user);
            else userService.addUsertoAdmin(user);
        }
        catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("redirect:/addUserByAdmin");
        }
        mv.setViewName("redirect:/users");//添加完后再次回到登录界面
        return mv;
    }
}
