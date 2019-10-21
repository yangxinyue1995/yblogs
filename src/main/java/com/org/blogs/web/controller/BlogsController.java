package com.org.blogs.web.controller;

import com.org.blogs.entity.BlogsBody;
import com.org.blogs.entity.Comments;
import com.org.blogs.service.BlogsBodyService;
import com.org.blogs.service.CommentsService;
import com.org.blogs.util.DateUtil;
import com.org.blogs.util.PageRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yxy on 2019/9/4.
 */
@Controller
@RequestMapping("blogs")
public class BlogsController  {

    @Autowired
    private BlogsBodyService blogsBodyService;
    @Autowired
    private CommentsService service;

    @RequestMapping(value = "/getListById",method = RequestMethod.GET)
    public String getListById(Model model,
                                   HttpServletRequest request, HttpSession session){
        String id = request.getParameter("id");
        BlogsBody list = blogsBodyService.getListById(Integer.parseInt(id));
        try {
            int weigh = list.getWeigh()+1;
            list.setWeigh(weigh);
            blogsBodyService.updateWeigh(list);
        }catch (Exception e){
            System.err.print(e);
        }
        List<BlogsBody> weighList = blogsBodyService.getListOrderByWeigh();
        List<BlogsBody> createList = blogsBodyService.getListOrderByCreateDateTime();
        List<Comments> comments = service.getCommentsByBlogsId(Integer.parseInt(id));
        for (int i = 0; i < comments.size(); i++) {
            Date d = DateUtil.parseDate(comments.get(i).getCreateDateTime(),"YYYY-mm-dd HH:ss");
            comments.get(i).setCreateDateTime(DateUtil.getDateTime(d));
        }
        model.addAttribute("bodyList", list);
        model.addAttribute("weighList", weighList);
        model.addAttribute("createList", createList);
        model.addAttribute("comments", comments);
        model.addAttribute("count", comments.size());
        return "frontPages/blogs/blogs_content";
    }

    private  List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    @RequestMapping("/getPage")
    public String getPage(Model model,
                              HttpServletRequest request, HttpSession session){
        String pageNo = request.getParameter("page");
        int pages=1;
        if(pageNo.equals("")){

        } else {
             pages = pageNo != null && Integer.parseInt(pageNo) >=0 ? Integer.parseInt(pageNo) : 1;
        }
        PageRoll page = new PageRoll();
        page.setPageNo(pages);
        page.setPageSize(5);

        List<BlogsBody> list = blogsBodyService.getList(page);
        List<BlogsBody> weighList = blogsBodyService.getListOrderByWeigh();
        List<BlogsBody> createList = blogsBodyService.getListOrderByCreateDateTime();

        model.addAttribute("bodyList", list);
        model.addAttribute("weighList", weighList);
        model.addAttribute("createList", createList);
        model.addAttribute("totalPage", page.getTotalPage());
        model.addAttribute("totalRows", page.getTotalRows());
        if(list.size()>0){
            model.addAttribute("nextPage", pages+1);
        } else {
            model.addAttribute("flag", true);
        }
        return "frontPages/index";
    }


    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String getSearchList(Model model,
                                HttpServletRequest request, HttpSession session){
        String str = request.getParameter("q");
        if(str!=null&&str!=""){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("str",str);
            List<BlogsBody> list = blogsBodyService.getSearchList(map);
            model.addAttribute("bodyList", list);
        }
        return "frontPages/blogs/search";
    }
}
