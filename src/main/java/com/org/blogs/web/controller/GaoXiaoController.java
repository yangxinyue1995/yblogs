package com.org.blogs.web.controller;

import com.org.blogs.entity.GaoXiao;
import com.org.blogs.entity.Photos;
import com.org.blogs.service.GaoXiaoService;
import com.org.blogs.util.PageRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by yxy on 2019/9/19.
 */
@Controller
@RequestMapping("gaoxiao")
public class GaoXiaoController {

    @Autowired
    private GaoXiaoService gaoXiaoService;

    @RequestMapping("/index")
    public String toindex(Model model,
                          HttpServletRequest request, HttpSession session){

        String pageNo = request.getParameter("page");
        int pages=1;
        pages = pageNo != null && Integer.parseInt(pageNo) >=0 ? Integer.parseInt(pageNo) : 1;
        PageRoll page = new PageRoll();
        page.setPageNo(pages);
        page.setPageSize(5);

        List<GaoXiao> list = gaoXiaoService.getList(page);
        model.addAttribute("bodyList",list);

        return "frontPages/gaoxiao/gaoxiao";
    }


    @RequestMapping("/getGaoxiao")
    public String getGaoxiao(Model model, HttpServletRequest request){

        String pageNo = request.getParameter("page");
        int pages=1;
        if(!pageNo.equals("")){
            pages = pageNo != null && Integer.parseInt(pageNo) >=0 ? Integer.parseInt(pageNo) : 1;
        }
        PageRoll page = new PageRoll();
        page.setPageNo(pages);
        page.setPageSize(5);

        List<GaoXiao> GaoXiao = gaoXiaoService.getList(page);
        model.addAttribute("bodyList",GaoXiao);
        model.addAttribute("totalPage", page.getTotalPage());
        model.addAttribute("totalRows", page.getTotalRows());
        if(GaoXiao.size()>0){
            model.addAttribute("nextPage", pages+1);
        } else {
            model.addAttribute("nextPage", 0);
        }
        return "frontPages/gaoxiao/gaoxiao";
    }
}
