package com.cupk.Controller;

import com.cupk.pojo.Comment;
import com.cupk.pojo.User;
import com.cupk.Service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    private User user=new User();

    @PostMapping("/circle/{id}/comment")
    public List<Comment> addComment(@PathVariable int id, @RequestBody Comment comment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("loginUser");
        System.out.println(user);
        comment.setCircleId(id);
        if (user != null) {
            comment.setUserId(user.getId());
            comment.setCreatedBy(user.getUsername());
        }
        commentService.insertComment(comment);
        return commentService.findCommentsByCircleId(id);
    }

    @GetMapping("/circle/{id}/comments")
    public List<Comment> getComments(@PathVariable int id) {
        return commentService.findCommentsByCircleId(id);
    }
}