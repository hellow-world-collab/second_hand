package com.cupk.Controller;

import com.cupk.pojo.CommunityPost;
import com.cupk.Service.CommunityPostsService;
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

import java.util.List;


@Controller
@Slf4j
public class CommunityPostsController {
    @Autowired
    private CommunityPostsService communityPostsService;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    private User user1=new User();



    @GetMapping("/toupdatepost/{id}")//跳转到修改图书页面
    public ModelAndView toUpdateCommunity(@PathVariable("id") int id) {
        CommunityPost post = communityPostsService.findCommunityPostById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("post", post);
        mv.setViewName("updatePost");
        return mv;
    }
    @GetMapping("/toupdateuserpost/{id}")//跳转到修改图书页面
    public ModelAndView toUpdateUser(@PathVariable("id") int id) {
        CommunityPost post = communityPostsService.findCommunityPostById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("post", post);
        mv.setViewName("updateUserPost");
        return mv;
    }
    @PostMapping("/updatePost")
    public ModelAndView updateCommunityPost(CommunityPost communityPost,MultipartFile imges)throws Exception {//执行修改图书 修改数据库
        ModelAndView mv = new ModelAndView();
        System.out.println(communityPost);
        try {
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                communityPost.setImgurl(url);
                System.out.println(communityPost.getImgurl());
                communityPostsService.updateUserPost(communityPost);
                System.out.println(communityPost);
            }
            else{
                communityPostsService.updateUserPostimgnull(communityPost);
            }
        }
        catch (Exception e) {
            mv.setViewName("redirect:/updatePost");
        }
        mv.setViewName("redirect:/posts");//修改图书成功后跳转至列表
        return mv;
    }
    @PostMapping("/updateUserPost")
    public ModelAndView updateUserPost(CommunityPost communityPost,MultipartFile imges)throws Exception {//执行修改图书 修改数据库
        ModelAndView mv = new ModelAndView();
        try {
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                communityPost.setImgurl(url);
                System.out.println(communityPost.getImgurl());
                communityPostsService.updateUserPost(communityPost);
                System.out.println(communityPost);
            }
            else{
                communityPostsService.updateUserPostimgnull(communityPost);
            }
        }
        catch (Exception e) {
            mv.setViewName("redirect:/updateUserPost");
        }


        mv.setViewName("redirect:/userposts");//修改图书成功后跳转至列表
        return mv;
    }
    @GetMapping("/deletePost/{id}")//删除图书
    public ModelAndView deleteCommunityPost(@PathVariable("id") int id) {//执行删除图书 修改数据库
        communityPostsService.deleteCommunityPost(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/posts");//删除图书成功后跳转至列表
        return mv;
    }

    @GetMapping("/deletepost/{id}")//删除图书
    public ModelAndView deleteUserPost(@PathVariable("id") int id) {//执行删除图书 修改数据库
        communityPostsService.deleteCommunityPost(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/userposts");//删除图书成功后跳转至列表
        return mv;
    }
    @RequestMapping("/searchPostsByStr")//模糊查询图书
    public String searchCommunityPostsByStr(@RequestParam(value = "searchStr") String searchStr, Model model){
        List<CommunityPost> posts = communityPostsService.searchCommunityPostsByStr(searchStr);
        PageInfo<CommunityPost> page=new PageInfo<>(posts);
        model.addAttribute("page",page);
        return "CommunityPostslist";
    }
    @GetMapping("/posts")
    public ModelAndView findAllBookts(@RequestParam(value="start",defaultValue = "1") int start,
                                     @RequestParam(value="size",defaultValue = "6") int size){
        PageHelper.startPage(start,size,"id asc");
        List<CommunityPost>posts=communityPostsService.findAllCommunityPosts();
        PageInfo<CommunityPost> page=new PageInfo<>(posts);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",page);
        mv.setViewName("CommunityPostslist");
        return mv;
    }

    @GetMapping("/userposts")
    public ModelAndView findAllPosts(@RequestParam(value="start",defaultValue = "1") int start,
                                     @RequestParam(value="size",defaultValue = "6") int size,HttpServletRequest request){
        PageHelper.startPage(start,size,"id asc");
        HttpSession session=request.getSession();
        user1=(User)session.getAttribute("loginUser");
        List<CommunityPost> posts=communityPostsService.findAllCommunityPostByUId(user1.getId());
        PageInfo<CommunityPost> page=new PageInfo<>(posts);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page",page);
        mv.setViewName("UserPostslist");
        return mv;
    }

    @GetMapping("/deletePosts")
    public ModelAndView deleteCommunityPosts(int[] id){//批量删除图书
        communityPostsService.deleteCommunityPosts(id);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:/posts");//删除成功后
        return mv;
    }

    @GetMapping("/toaddUserPosts")//跳转到添加图书页面
    public String addBook() {
        return "addUserPosts";
    }

    @PostMapping("/addUserPosts")
    public ModelAndView addUserPost(CommunityPost communityPost, MultipartFile imges)throws Exception {//执行添加图书 修改数据库
        ModelAndView mv = new ModelAndView();
        try {//调用阿里云OSS
            if (!imges.isEmpty()) {
                log.info("文件上传，文件名：{}", imges.getOriginalFilename());
                String url = aliOSSUtils.upload(imges);
                log.info("文件上传完成，文件访问的url:{}", url);
                communityPost.setImgurl(url);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("redirect:/addUserPosts");
        }
        communityPostsService.addUserPost(communityPost);

        mv.setViewName("redirect:/userposts");//添加图书成功后跳转至列表
        return mv;
    }

    @GetMapping("/status")
    public ModelAndView countStatus() {
        ModelAndView mv = new ModelAndView();
        int passed = communityPostsService.countStateZ();
        int fpassed = communityPostsService.countStateG();
        System.out.println(passed);
        System.out.println(fpassed);

        // 设置视图名称
        mv.setViewName("Mangeindex");
        // 将 passedCount 添加到模型中
        mv.addObject("passedCount", passed);
        mv.addObject("fpassedCount", fpassed);
        // 添加额外的数据作为 JSON 响应
//        mv.addObject("jsonData", Map.of("passed", passed,"fpassed", fpassed));
        return mv;
    }




}
