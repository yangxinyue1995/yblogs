package com.org.blogs.web.controller;

import com.org.blogs.entity.Comments;
import com.org.blogs.service.CommentsService;
import com.org.blogs.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by yxy on 2019/9/12.
 */
@Controller
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentsService service;

    @RequestMapping("/saveComments")
    @ResponseBody
    public String saveComments(Model model,
                             HttpServletRequest request){
        String blogsId = request.getParameter("blogsId");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String comments = request.getParameter("answer_content");

        Comments c = new Comments();
        c.setBlogsId(Integer.parseInt(blogsId));
        c.setComments(comments);
        c.setCreateDateTime(DateUtil.getDateTime(new Date()));
        c.setUserId(Integer.parseInt(userId));
        c.setUserName(userName);
        if(service.saveComments(c)>0){
            return "提交评论成功！";
        }
        return "redirect:/500";
    }
}
