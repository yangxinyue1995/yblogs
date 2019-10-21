package com.org.blogs.service.impl;

import com.org.blogs.entity.Comments;
import com.org.blogs.mapper.CommentsMapper;
import com.org.blogs.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxy on 2019/9/11.
 */
@Service("CommentsService")
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper mapper;

    @Override
    public List<Comments> getCommentsByBlogsId(Integer blogsId) {
        return mapper.getCommentsByBlogsId(blogsId);
    }

    @Override
    public int saveComments(Comments comments) {
        return mapper.saveComments(comments);
    }
}
