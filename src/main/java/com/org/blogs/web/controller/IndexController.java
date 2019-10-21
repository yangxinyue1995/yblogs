package com.org.blogs.web.controller;

import com.org.blogs.entity.BlogsBody;
import com.org.blogs.service.BlogsBodyService;
import com.org.blogs.service.UserService;
import com.org.blogs.util.PageRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogsBodyService blogsBodyService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "inde", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex(Model model,HttpServletRequest request, HttpSession session) {

        String pageNo = request.getParameter("page");
        int pages = pageNo != null ? Integer.parseInt(pageNo) : 1;
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
        model.addAttribute("nextPage", page.getNextPage());
        return "frontPages/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex1(Model model) {
        return "frontPages/index";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String err(Model model) {
        return "frontPages/500";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String lost(Model model) {
        return "frontPages/404";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model) {

        return "frontPages/index";
    }
}
