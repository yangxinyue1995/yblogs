package com.org.blogs.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.org.blogs.entity.BlogsBody;
import com.org.blogs.entity.UserInfo;
import com.org.blogs.service.BlogsBodyService;
import com.org.blogs.util.DateTimeUtil;
import com.org.blogs.util.R;
import netscape.javascript.JSObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.owasp.esapi.codecs.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by yxy on 2019/9/19.
 */
@Controller
@RequestMapping("edit")
public class EditController {

    @Autowired
    private BlogsBodyService blogsBodyService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toindex(Model model,HttpServletRequest request, HttpSession session){
        return "frontPages/blogs/edit";
    }
    @RequestMapping(value = "/saveBlogs",method = RequestMethod.POST)
    @ResponseBody
    public R saveBlogs(Model model, HttpServletRequest request, HttpSession session){
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String tag = request.getParameter("tag");
        String edit = request.getParameter("edit");
        edit = StringEscapeUtils.unescapeHtml(edit);
        UserInfo u = (UserInfo) session.getAttribute("manager");
        JSONObject object = new JSONObject();
        BlogsBody b = new BlogsBody();
        b.setWeigh(0);
        b.setContent(edit);
        b.setCreatime(DateTimeUtil.getCurTime());
        b.setAuthor(u.getName());
        b.setTags(tag);
        b.setTitle(title);
        b.setType(type);
        if(blogsBodyService.saveBlogs(b)>0){
            return  R.ok();
        }
       return R.error();
    }


}
