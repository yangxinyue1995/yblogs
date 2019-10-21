package com.org.blogs.service;

import com.org.blogs.entity.GaoXiao;
import com.org.blogs.util.PageRoll;

import java.util.List;

/**
 * Created by yxy on 2019/9/19.
 */
public interface GaoXiaoService {

    List<GaoXiao> getList(PageRoll page);
}
