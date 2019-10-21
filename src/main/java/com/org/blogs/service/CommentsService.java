package com.org.blogs.service;

import com.org.blogs.entity.Comments;

import java.util.List;

/**
 * Created by yxy on 2019/9/11.
 */
public interface CommentsService {

    /**
     * 通过文章id获取评论
     * @param blogsId
     * @return
     */
    List<Comments> getCommentsByBlogsId(Integer blogsId);

    /**
     * 保存评论
     */
    int saveComments(Comments comments);
}
