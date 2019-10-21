package com.org.blogs.service.impl;

import com.org.blogs.entity.GaoXiao;
import com.org.blogs.mapper.GaoXiaoMapper;
import com.org.blogs.service.GaoXiaoService;
import com.org.blogs.util.PageRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2019/9/19.
 */
@Service("GaoXiaoService")
public class GaoXiaoServiceImpl implements GaoXiaoService {

    @Autowired
    private GaoXiaoMapper gaoXiaoMapper;

    @Override
    public List<GaoXiao> getList(PageRoll page) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("startRow", page.getStartRow());
        param.put("rowNum", page.getPageSize());
        page.setParams(param);
        return gaoXiaoMapper.getList(page);
    }
}
