package com.org.blogs.service;

import com.org.blogs.entity.BlogsBody;
import com.org.blogs.util.PageRoll;

import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2019/9/4.
 */
public interface BlogsBodyService {
    /**
     * 获得所有的文章
     */
    List<BlogsBody> getList(PageRoll page);

    /**
     * 通过id获取文章详情
     * @return
     */
    BlogsBody getListById(int id);
    /**
     * 根据权重查询热点排序
     */
    List<BlogsBody> getListOrderByWeigh();
    /**
     * 查询最近更新时间排序
     */
    List<BlogsBody> getListOrderByCreateDateTime();

    /**
     * 查询搜索
     * @return
     */
    List<BlogsBody> getSearchList(Map<String, Object> map);

    /**
     * 增加权重在基础上+1
     * @return
     */
    int updateWeigh(BlogsBody blogsBody);

    /**
     *
     * 保存博客
     * @param blogsBody
     * @return
     */
    int saveBlogs(BlogsBody blogsBody);
}
