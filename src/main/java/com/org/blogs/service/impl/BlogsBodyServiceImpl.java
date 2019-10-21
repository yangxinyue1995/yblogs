package com.org.blogs.service.impl;

import com.org.blogs.entity.BlogsBody;
import com.org.blogs.mapper.BlogsBodyMapper;
import com.org.blogs.service.BlogsBodyService;
import com.org.blogs.util.PageRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2019/9/4.
 */
@Service("BlogsBodyService")
public class BlogsBodyServiceImpl implements BlogsBodyService {

    @Autowired
    private BlogsBodyMapper mapper;

    @Override
    public List<BlogsBody> getList(PageRoll page) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("startRow", page.getStartRow());
        param.put("rowNum", page.getPageSize());

        page.setParams(param);
        return mapper.getList(page);
    }

    @Override
    public BlogsBody getListById(int id) {
        return mapper.getListById(id);
    }

    @Override
    public List<BlogsBody> getListOrderByWeigh() {
        return mapper.getListOrderByWeigh();
    }

    @Override
    public List<BlogsBody> getListOrderByCreateDateTime() {
        return mapper.getListOrderByCreateDateTime();
    }

    @Override
    public List<BlogsBody> getSearchList(Map<String, Object> map) {
        return mapper.getSearchList(map);
    }

    @Override
    public int updateWeigh(BlogsBody blogsBody) {
        return mapper.updateWeigh(blogsBody);
    }

    @Override
    public int saveBlogs(BlogsBody blogsBody) {
        return mapper.saveBlogs(blogsBody);
    }
}
